package com.sapient.card.app.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ FIELD , PARAMETER , METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = {LuhnValidation.class})
public @interface CreditCard {

    String message() default "Invalid Card number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
