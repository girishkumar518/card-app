package com.sapient.card.app.constants;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public enum Errors {

    INVALID_CARD_NUMBER        ("ERROR_0001","Invalid card number provided");

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
        errorStatusMap.put(INVALID_CARD_NUMBER.getCode(), HttpStatus.BAD_REQUEST);
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
