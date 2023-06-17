package com.exampleM.Minh.validator;

import com.exampleM.Minh.validator.annotation.ValidCategoryId;
import com.exampleM.Minh.entity.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {

    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return  category !=null && category.getId() !=null;
    }

}
