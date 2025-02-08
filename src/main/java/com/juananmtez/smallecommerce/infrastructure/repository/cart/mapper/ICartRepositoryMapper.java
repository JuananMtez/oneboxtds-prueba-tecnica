package com.juananmtez.smallecommerce.infrastructure.repository.cart.mapper;

import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.infrastructure.repository.cart.entity.CartEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICartRepositoryMapper {

    Cart asCart(CartEntity cartEntity);

    List<Cart> asCartList(List<CartEntity> cartEntityList);

    CartEntity asCartEntity(Cart cart);
}
