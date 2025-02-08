package com.juananmtez.smallecommerce.domain.database;

import com.juananmtez.smallecommerce.infrastructure.repository.cart.entity.CartEntity;
import java.util.List;
import java.util.UUID;

public interface IInMemoryDatabase {

    List<CartEntity> findAllCartEntities();

    CartEntity findCartEntityById(UUID cartId);

    CartEntity saveCartEntity(CartEntity cart);

    CartEntity updateCartEntity(CartEntity cart);

    void deleteAllUnusedCartEntities(int expirationTime);

    void deleteCartEntityById(UUID cartId);
}
