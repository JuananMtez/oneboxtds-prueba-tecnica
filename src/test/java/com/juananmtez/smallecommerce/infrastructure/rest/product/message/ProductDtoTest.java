package com.juananmtez.smallecommerce.infrastructure.rest.product.message;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ProductDtoTest {

    @Test
    public void productDtoConstructorWithParameters() {
        ProductDto product = new ProductDto(UUID.randomUUID(), "description", new BigDecimal(10));

        assertNotNull(product.getId());
        assertNotNull(product.getDescription());
        assertNotNull(product.getAmount());
    }
}
