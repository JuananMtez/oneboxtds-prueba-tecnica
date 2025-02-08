package com.juananmtez.smallecommerce.infrastructure.rest.cart.mapper;

import com.juananmtez.smallecommerce.domain.model.Cart;
import com.juananmtez.smallecommerce.infrastructure.rest.cart.message.CartDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICartRestMapper {

    CartDto asCartDto(Cart cart);

    List<CartDto> asCartDtoList(List<Cart> cartList);
}
