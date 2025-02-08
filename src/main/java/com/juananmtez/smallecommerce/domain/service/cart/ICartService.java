package com.juananmtez.smallecommerce.domain.service.cart;

import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.domain.model.Product;
import java.util.List;
import java.util.UUID;

public interface ICartService {

    Cart createCart(List<Product> products);

    Cart getCart(UUID cartId);

    List<Cart> getAllCarts();

    Cart addProductsToCart(UUID cartId, List<Product> products);

    void deleteCartById(UUID cartId);

    Cart removeProductsFromCart(UUID cartId, List<UUID> productIds);

    void deleteUnusedCart(int expiration);
}
