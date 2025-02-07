package prueba.tecnica.oneboxtds.infrastructure.rest.cart.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.infrastructure.rest.cart.message.CartDto;

@Mapper(componentModel = "spring")
public interface ICartRestMapper {

  CartDto asCartDto(Cart cart);

  List<CartDto> asCartDtoList(List<Cart> cartList);
}
