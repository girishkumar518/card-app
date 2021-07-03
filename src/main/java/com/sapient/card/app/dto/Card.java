package com.sapient.card.app.dto;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sapient.card.app.validation.CreditCard;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class Card {

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    @CreditCard
    private String number;

    private Double limit;

}
