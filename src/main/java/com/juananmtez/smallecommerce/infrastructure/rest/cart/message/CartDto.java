package com.juananmtez.smallecommerce.infrastructure.rest.cart.message;

import com.juananmtez.smallecommerce.infrastructure.rest.product.message.ProductDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartDto {
    private UUID id;
    private List<ProductDto> products;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
