package com.sapient.card.app.dto;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter @NoArgsConstructor @ToString
public class Card {

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private String number;

    private Double limit;

}
