package com.juananmtez.smallecommerce.infrastructure.rest.error.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.juananmtez.smallecommerce.domain.exception.ErrorEnum;
import org.junit.jupiter.api.Test;

public class ErrorDtoTest {

    @Test
    public void productDtoConstructorWithParameters() {
        ErrorDto error = new ErrorDto("message", ErrorEnum.UNEXPECTED_ERROR);

        assertEquals("message", error.getMessage());
        assertNotNull(error.getMessage());
        assertEquals(ErrorEnum.UNEXPECTED_ERROR, error.getError());
        assertNotNull(error.getError());
    }
}
