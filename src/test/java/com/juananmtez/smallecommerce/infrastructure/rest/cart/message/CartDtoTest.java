package com.juananmtez.smallecommerce.infrastructure.rest.cart.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.juananmtez.smallecommerce.infrastructure.rest.product.message.ProductDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CartDtoTest {

    @Test
    public void cartDtoConstructorWithSpecificParameters() {
        UUID id = UUID.randomUUID();
        ProductDto product1 =
                new ProductDto(UUID.randomUUID(), "Product 1", new BigDecimal("10.0"));
        ProductDto product2 =
                new ProductDto(UUID.randomUUID(), "Product 2", new BigDecimal("10.0"));
        List<ProductDto> products = Arrays.asList(product1, product2);
        LocalDateTime creationDate = LocalDateTime.of(2025, 2, 7, 10, 0, 0, 0);
        LocalDateTime modificationDate = LocalDateTime.of(2025, 2, 7, 10, 30, 0, 0);

        CartDto cartEntity = new CartDto(id, products, creationDate, modificationDate);

        assertEquals(id, cartEntity.getId());

        assertNotNull(cartEntity.getProducts());
        assertEquals(2, cartEntity.getProducts().size());

        assertNotNull(cartEntity.getCreationDate());
        assertNotNull(cartEntity.getModificationDate());

        assertEquals(creationDate, cartEntity.getCreationDate());
        assertEquals(modificationDate, cartEntity.getModificationDate());
    }
}
