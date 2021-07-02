package com.sapient.card.app.exception;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.sapient.card.app.constants.Errors;
import com.sapient.card.app.dto.Response;
import org.springframework.http.HttpStatus;

public class CardAppException extends RuntimeException {

    private Response response;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * CardAppException with errorCode and message
     * @param errorCode
     * @param errorMessage
     */
    public CardAppException(String errorCode, String errorMessage) {
        this(errorCode, errorMessage, Errors.getHttpStatus(errorCode));
    }

    /**
     * CardAppException with errorCode and message
     * @param error
     */
    public CardAppException(Errors error) {
        super(error.getMessage());
        this.response = createResponse(error.getCode(), error.getMessage());
        this.setHttpStatus(Errors.getHttpStatus(error));
    }

    public CardAppException(Throwable t, Errors error) {
        super(t);
        this.response = createResponse(error.getCode(), error.getMessage());
        this.setHttpStatus(Errors.getHttpStatus(error));
    }

    /**
     * CardAppException with http status code, errorCode and message
     * @param errorCode
     * @param errorMessage
     * @param httpStatus
     */
    public CardAppException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.response = createResponse(errorCode, errorMessage);
        this.setHttpStatus(httpStatus);
    }

    /**
     *
     * @param response
     */
    public CardAppException(Response response){
        this.response = response;
    }

    /**
     * CardAppException with error and cause
     * @param error
     * @param cause
     */
    public CardAppException(Errors error, Throwable cause) {
        this(error);
        this.initCause(cause);
    }

    public CardAppException(Throwable t) {
        super(t);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Response getResponse() {return response;}

    public void setResponse(Response response) {this.response = response;}

    public static Response createResponse(String errorCode, String errorMessage) {

        Response response = new Response();
        response.setStatusCode(errorCode);
        response.setStatusMessage(errorMessage);

        return response;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorCode: ").append(this.response.getStatusCode()).
                append("; ErrorMessage: ").append(this.response.getStatusMessage());
        return sb.toString();
    }

}
