package com.juananmtez.smallecommerce.infrastructure.repository.product.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ProductEntityTest {
    @Test
    public void productConstructorWithParameters() {
        ProductEntity product =
                new ProductEntity(UUID.randomUUID(), "description", new BigDecimal(10));

        assertNotNull(product.getId());
        assertNotNull(product.getDescription());
        assertNotNull(product.getAmount());
    }
}
