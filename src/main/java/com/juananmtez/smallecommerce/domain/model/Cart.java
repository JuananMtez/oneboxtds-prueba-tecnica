package com.juananmtez.smallecommerce.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {

    private UUID id;
    private List<Product> products;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    public Cart() {
        this.id = UUID.randomUUID();
        this.products = new ArrayList<>();
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }

    public Cart(List<Product> products) {
        this.id = UUID.randomUUID();
        this.products = new ArrayList<>(List.copyOf(products));
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }

    public Cart(
            UUID id,
            List<Product> products,
            LocalDateTime creationDate,
            LocalDateTime modificationDate) {
        this.id = id;
        this.products = new ArrayList<>(List.copyOf(products));
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public void removeProductsByIds(List<UUID> productIds) {
        products.removeIf(product -> productIds.contains(product.getId()));
        modificationDate = LocalDateTime.now();
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
        this.modificationDate = LocalDateTime.now();
    }
}
