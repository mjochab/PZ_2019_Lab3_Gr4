package com.app.library.repository;

import com.app.library.model.Book;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public interface MockedBookRepository extends BookRepository {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    default  <S extends Book> S save(S entity) {
        Set<ConstraintViolation<S>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return entity;
    }
}
