package prueba.tecnica.oneboxtds.infrastructure.repository.database;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import prueba.tecnica.oneboxtds.domain.database.IInMemoryDatabase;
import prueba.tecnica.oneboxtds.domain.exception.CartAlreadyExistsException;
import prueba.tecnica.oneboxtds.domain.exception.CartNotFoundException;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;

@Component
public class InMemoryDatabaseImpl implements IInMemoryDatabase {

  private final ConcurrentHashMap<UUID, CartEntity> cartEntities;

  public InMemoryDatabaseImpl() {
    this.cartEntities = new ConcurrentHashMap<>();
  }

  public List<CartEntity> findAllCartEntities() {
    return List.copyOf(cartEntities.values());
  }

  public CartEntity findCartEntityById(UUID cartId) {
    if (cartEntities.containsKey(cartId)) {
      return cartEntities.get(cartId);
    }
    throw new CartNotFoundException("Cart not found in database");
  }

  public CartEntity saveCartEntity(CartEntity cart) {
    if (!cartEntities.containsKey(cart.getId())) {
      cartEntities.put(cart.getId(), cart);
      return cart;
    }
    throw new CartAlreadyExistsException("Cart already exists in database");
  }

  public CartEntity updateCartEntity(CartEntity cart) {
    if (cartEntities.containsKey(cart.getId())) {
      cartEntities.put(cart.getId(), cart);
      return cart;
    }

    throw new CartNotFoundException("Cart not found in database");
  }

  @Override
  public void deleteAllUnusedCartEntities(int expirationTime) {
    LocalDateTime expirationThreshold = LocalDateTime.now().minusMinutes(expirationTime);

    cartEntities
        .entrySet()
        .removeIf(entry -> entry.getValue().getModificationDate().isBefore(expirationThreshold));
  }

  @Override
  public void deleteCartEntityById(UUID cartId) {
    if (!cartEntities.containsKey(cartId)) {
      throw new CartNotFoundException("Cart not found in database");
    }
    cartEntities.remove(cartId);
  }
}
