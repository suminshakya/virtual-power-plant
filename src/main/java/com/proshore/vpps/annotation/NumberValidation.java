package com.proshore.vpps.annotation;

import com.proshore.vpps.utils.RequiredConstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NumberValidation implements ConstraintValidator<ValidateNumber, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(RequiredConstant.RegularExpression.POSITIVE_VALUE);
        return pattern.matcher(String.valueOf(value)).matches();
    }

}
