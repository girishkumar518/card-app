package com.sapient.card.app.validation;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LuhnValidation implements ConstraintValidator<CreditCard , String> {

    private String cardNumber;


    @Override
    public void initialize(CreditCard card) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        int sum = 0;
        int digit;
        for(int i=0 ; i < value.length() ; i++){

            digit = Integer.parseInt(""+value.charAt(i));

            //for every 2nd digit we need to double
            if( i % 2 == 1)
                digit = digit * 2 ;

            // we will add 2 digits after doubling
            sum += digit % 10;
            sum += digit /10;

        }

        return (sum % 10 == 0);
    }
}
