package com.juananmtez.smallecommerce.infrastructure.repository.cart.entity;

import com.juananmtez.smallecommerce.infrastructure.repository.product.entity.ProductEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class CartEntity {

    private UUID id;
    private List<ProductEntity> products;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
