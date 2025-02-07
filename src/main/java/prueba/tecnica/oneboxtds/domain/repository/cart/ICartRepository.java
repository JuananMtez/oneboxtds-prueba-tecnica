package prueba.tecnica.oneboxtds.domain.repository.cart;

import java.util.List;
import java.util.UUID;
import prueba.tecnica.oneboxtds.domain.model.Cart;

public interface ICartRepository {

  List<Cart> findAllCarts();

  Cart findCartById(UUID cartId);

  Cart saveCart(Cart cart);

  Cart updateCart(Cart cart);

  void deleteCartById(UUID cartId);
}
