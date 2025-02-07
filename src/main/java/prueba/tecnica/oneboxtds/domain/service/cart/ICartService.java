package prueba.tecnica.oneboxtds.domain.service.cart;

import java.util.List;
import java.util.UUID;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.domain.model.Product;

public interface ICartService {

  public Cart createCart(List<Product> products);

  public Cart getCart(UUID cartId);

  public List<Cart> getAllCarts();

  public Cart addProductsToCart(UUID cartId, List<Product> products);

  public void deleteCartById(UUID cartId);

  public Cart removeProductsFromCart(UUID cartId, List<UUID> productIds);

  public void deleteUnusedCart();
}
