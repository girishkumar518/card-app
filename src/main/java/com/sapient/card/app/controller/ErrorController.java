package com.sapient.card.app.controller;

import com.sapient.card.app.constants.Errors;
import com.sapient.card.app.dto.Response;
import com.sapient.card.app.exception.CardAppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/*
 *  Created by Girish Kumar CH on 7/2/21
 *  All the errors will be handled in this class
 */
@Controller
@Slf4j
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {


    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public ResponseEntity<?> errorResponse(final HttpServletRequest request) {

        WebRequest requestAttributes = new ServletWebRequest(request);
        Throwable throwable = errorAttributes.getError(requestAttributes);
        HttpStatus status = this.getStatus(request);

        Response response;

        if (throwable instanceof CardAppException) {

            CardAppException se = (CardAppException) throwable;
            response = se.getResponse();
            status = HttpStatus.BAD_REQUEST;

        } else if (throwable instanceof HttpMessageNotReadableException) {

            log.error("Invalid request body", throwable);
            CardAppException se = new CardAppException(Errors.INVALID_MSG);
            response = se.getResponse();
            status = se.getHttpStatus();

        } else if (throwable instanceof MethodArgumentNotValidException) {

            log.error("Invalid request body validation error {} ", throwable);

            BindingResult bindingResult = ((MethodArgumentNotValidException) throwable).getBindingResult();

            CardAppException se = null;

            bindingResult.getFieldErrors().forEach(c->c.getField());
            
            if(bindingResult.hasFieldErrors())
                if(bindingResult.getFieldErrors().stream().filter(e->e.getField().equalsIgnoreCase("number")).findAny().isPresent())
                    se =new CardAppException(Errors.VALIDATION_INVALID_CARD_NUMBER);
                else
                    se =new CardAppException(Errors.VALIDATION_ERROR_REQUEST_BODY);
           else
                se =new CardAppException(Errors.INVALID_MSG);

            
            response = se.getResponse();
            status = se.getHttpStatus();

        }else if (throwable instanceof DataIntegrityViolationException) {

            log.error("Database Error ", throwable);
            CardAppException se = new CardAppException(Errors.CARD_NUMBER_EXISTED);
            response = se.getResponse();
            status = se.getHttpStatus();

        }

        else {
            if(throwable != null) {
                log.error("Unmapped error occurred.", throwable);
                response = createResponse(Errors.UNMAPPED);
            } else {
                log.error("Unexpected exeption caught in error controller with null throwable");
                response =createResponse(Errors.UNMAPPED);
            }
        }
        return new ResponseEntity<>(response, status);
    }

    public static Response createResponse(Errors error) {

        Response response = new Response();
        response.setStatusCode(error.getCode());
        response.setStatusMessage(error.getMessage());

        return response;
    }

}
