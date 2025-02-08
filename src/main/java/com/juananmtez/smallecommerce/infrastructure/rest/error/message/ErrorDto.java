package com.juananmtez.smallecommerce.infrastructure.rest.error.message;

import com.juananmtez.smallecommerce.domain.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private ErrorEnum error;
}
