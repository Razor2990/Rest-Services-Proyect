package com.proyect.services.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberFormatValidator.class)
public @interface PhoneNumberFormat {

    String message() default "El número de teléfono no sigue un formato válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
