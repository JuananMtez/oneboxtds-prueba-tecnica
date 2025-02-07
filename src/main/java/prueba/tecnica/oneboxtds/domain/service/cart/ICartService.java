package prueba.tecnica.oneboxtds.domain.service.cart;

import java.util.List;
import java.util.UUID;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.domain.model.Product;

public interface ICartService {

  Cart createCart(List<Product> products);

  Cart getCart(UUID cartId);

  List<Cart> getAllCarts();

  Cart addProductsToCart(UUID cartId, List<Product> products);

  void deleteCartById(UUID cartId);

  Cart removeProductsFromCart(UUID cartId, List<UUID> productIds);

  void deleteUnusedCart(int expiration);
}
