package prueba.tecnica.oneboxtds.infrastructure.repository.cart.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;

@Mapper(componentModel = "spring")
public interface ICartRepositoryMapper {

  Cart asCart(CartEntity cartEntity);

  List<Cart> asCartList(List<CartEntity> cartEntityList);

  CartEntity asCartEntity(Cart cart);
}
