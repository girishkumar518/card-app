package com.sapient.card.app.constants;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public enum Errors {

    VALIDATION_INVALID_CARD_NUMBER ("VALIDATION_INVALID_CARD_NUMBER","Invalid card number provided"),
    VALIDATION_ERROR_REQUEST_BODY("VALIDATION_ERROR_REQUEST_BODY","Validation Error in request body"),
    INVALID_MSG("ERROR_INVALID_MSG", "Invalid Request body"),
    UNMAPPED("ERROR_UNMAPPED", "Server cannot handle this request"),
    INTERNAL_SERVER("ERROR_INTERNAL_SERVER","Operation failed due to some internal server error"),
    CARD_NUMBER_EXISTED ("CARD_NUMBER_EXISTED","Card number already existed");



    private final String code;
    private final String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {return code;}
    public String getMessage() {return message;}

    private static final HashMap<String, HttpStatus> errorStatusMap = new HashMap<>();

    static {
        errorStatusMap.put(VALIDATION_INVALID_CARD_NUMBER.getCode(), HttpStatus.BAD_REQUEST);
        errorStatusMap.put(INVALID_MSG.getCode(), HttpStatus.BAD_REQUEST);
        errorStatusMap.put(VALIDATION_ERROR_REQUEST_BODY.getCode(), HttpStatus.BAD_REQUEST);
        errorStatusMap.put(UNMAPPED.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        errorStatusMap.put(INTERNAL_SERVER.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        errorStatusMap.put(CARD_NUMBER_EXISTED.getCode(), HttpStatus.BAD_REQUEST);


    }

    public static HttpStatus getHttpStatus(Errors error) {
        return getHttpStatus(error.getCode());
    }

    public static HttpStatus getHttpStatus(String errorCode) {
        HttpStatus statusCode;
        statusCode = errorStatusMap.get(errorCode);
        if (statusCode == null) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return statusCode;
    }


    @Override
    public String toString() {
        return "Errors{" +
                "errorCode='" + code + '\'' +
                ", errorMessage='" + message + '\'' +
                '}';
    }
}
