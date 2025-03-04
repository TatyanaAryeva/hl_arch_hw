package org.hlarch.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hlarch.model.domain.Gender;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValueOf, String> {
    
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValueOf constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(value));
    }
}
