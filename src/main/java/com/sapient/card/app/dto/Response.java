package com.sapient.card.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Response {

    private String statusCode;
    private String statusMessage;

}