package com.proyect.services.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberFormatValidator implements ConstraintValidator<PhoneNumberFormat, String> {

    @Override
    public void initialize(PhoneNumberFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // Implementa la lógica de validación para el formato del número de teléfono
        // En este ejemplo, solo verifica que el número de teléfono no sea nulo y no esté vacío
        return phoneNumber != null && !phoneNumber.trim().isEmpty();
    }
}
