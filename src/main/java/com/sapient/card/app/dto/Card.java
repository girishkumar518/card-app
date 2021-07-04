package com.sapient.card.app.dto;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sapient.card.app.constants.Errors;
import com.sapient.card.app.validation.Cheap;
import com.sapient.card.app.validation.CreditCard;
import com.sapient.card.app.validation.Expensive;
import lombok.*;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.sapient.card.app.constants.ApplicationConstants.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
@GroupSequence(value={Card.class, Cheap.class, Expensive.class})
public class Card {

    private Integer id;

    @NotBlank(groups = Cheap.class)
    private String name;

    @NotNull(groups = Cheap.class)
    //validation card should be numeric
    @Pattern( regexp = "^([1-9][0-9]*)$", message = VALIDATION_INVALID_CARD_NUMBER_NUMERIC_MSG ,groups = Cheap.class )
    //validation for max size 19 characters
    @Size(max = 19 , message = VALIDATION_INVALID_CARD_NUMBER_LENGTH_MSG, groups = Cheap.class)
    @CreditCard (groups = Expensive.class, message = VALIDATION_INVALID_CARD_NUMBER_MSG)
    private String number;

    private Double limit;

}
