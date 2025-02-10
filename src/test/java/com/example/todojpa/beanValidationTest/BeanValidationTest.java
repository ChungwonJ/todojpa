package com.example.todojpa.beanValidationTest;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanValidationTest {
    @Autowired
    private Validator validator;

    public BeanValidationTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }
    @Test
    void signUpRequestDtoValidation() {

    }
}
