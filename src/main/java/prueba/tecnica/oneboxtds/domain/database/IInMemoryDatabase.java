package prueba.tecnica.oneboxtds.domain.database;

import java.util.List;
import java.util.UUID;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;

public interface IInMemoryDatabase {

  public List<CartEntity> findAllCartEntities();

  public CartEntity findCartEntityById(UUID cartId);

  public CartEntity saveCartEntity(CartEntity cart);

  public CartEntity updateCartEntity(CartEntity cart);

  public void deleteCartEntityById(UUID cartId);
}
