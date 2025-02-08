package com.juananmtez.smallecommerce.domain.repository.cart;

import com.juananmtez.smallecommerce.domain.model.Cart;
import java.util.List;
import java.util.UUID;

public interface ICartRepository {

    List<Cart> findAllCarts();

    Cart findCartById(UUID cartId);

    Cart saveCart(Cart cart);

    Cart updateCart(Cart cart);

    void deleteCartById(UUID cartId);

    void deleteAllUnusedCarts(int expiration);
}
