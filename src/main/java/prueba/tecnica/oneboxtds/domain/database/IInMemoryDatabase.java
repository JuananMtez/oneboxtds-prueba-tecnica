package prueba.tecnica.oneboxtds.domain.database;

import java.util.List;
import java.util.UUID;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;

public interface IInMemoryDatabase {

  List<CartEntity> findAllCartEntities();

  CartEntity findCartEntityById(UUID cartId);

  CartEntity saveCartEntity(CartEntity cart);

  CartEntity updateCartEntity(CartEntity cart);

  void deleteCartEntityById(UUID cartId);
}
