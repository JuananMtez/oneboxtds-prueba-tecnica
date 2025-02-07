package prueba.tecnica.oneboxtds.domain.repository.cart;

import java.util.List;
import java.util.UUID;
import prueba.tecnica.oneboxtds.domain.model.Cart;

public interface ICartRepository {

  public List<Cart> findAllCarts();

  public Cart findCartById(UUID cartId);

  public Cart saveCart(Cart cart);

  public Cart updateCart(Cart cart);

  public void deleteCartById(UUID cartId);
}
