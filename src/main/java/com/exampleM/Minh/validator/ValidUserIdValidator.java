package com.exampleM.Minh.validator;

import com.exampleM.Minh.validator.annotation.ValidUserId;
import com.exampleM.Minh.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context)
    {
        if(user==null)
        {
            return true;
        }
        return user.getId() !=null;
    }
}
