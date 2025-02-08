package com.juananmtez.smallecommerce.infrastructure.rest.product.message;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    @NotNull(message = "Id cannot be null")
    private UUID id;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
}
