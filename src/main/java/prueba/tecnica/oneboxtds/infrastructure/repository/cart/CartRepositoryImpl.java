package prueba.tecnica.oneboxtds.infrastructure.repository.cart;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import prueba.tecnica.oneboxtds.domain.database.IInMemoryDatabase;
import prueba.tecnica.oneboxtds.domain.exception.CartAlreadyExistsException;
import prueba.tecnica.oneboxtds.domain.exception.CartNotFoundException;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.domain.repository.cart.ICartRepository;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.mapper.ICartRepositoryMapper;

@Repository
public class CartRepositoryImpl implements ICartRepository {

  Logger logger = LoggerFactory.getLogger(CartRepositoryImpl.class);

  private static final String LOG_HEADER = "[REPOSITORY][CART]";

  private final IInMemoryDatabase database;

  private final ICartRepositoryMapper cartRepositoryMapper;

  public CartRepositoryImpl(
      IInMemoryDatabase database,
      @Qualifier("ICartRepositoryMapperImpl") ICartRepositoryMapper cartRepositoryMapper) {
    this.database = database;
    this.cartRepositoryMapper = cartRepositoryMapper;
  }

  @Override
  public List<Cart> findAllCarts() {
    logger.info("{} Get all carts", LOG_HEADER);
    try {
      return cartRepositoryMapper.asCartList(database.findAllCartEntities());
    } catch (Exception e) {
      logger.error("{} There was an error finding all carts", LOG_HEADER);
      throw e;
    }
  }

  @Override
  public Cart saveCart(Cart cart) {
    logger.info("{} Save cart {}", LOG_HEADER, cart);
    try {
      return cartRepositoryMapper.asCart(
          database.saveCartEntity(cartRepositoryMapper.asCartEntity(cart)));
    } catch (CartAlreadyExistsException e) {
      logger.warn("{} Card not saved since cart already exists", LOG_HEADER);
      throw e;
    } catch (Exception e) {
      logger.error("{} There was an error saving a cart {}", LOG_HEADER, cart);
      throw e;
    }
  }

  @Override
  public Cart updateCart(Cart cart) {
    logger.info("{} Update cart {}", LOG_HEADER, cart);
    try {
      return cartRepositoryMapper.asCart(
          database.updateCartEntity(cartRepositoryMapper.asCartEntity(cart)));
    } catch (CartNotFoundException e) {
      logger.warn("{} Card not updated since cart not found by id: {}", LOG_HEADER, cart.getId());
      throw e;
    } catch (Exception e) {
      logger.error("{} There was an error updating a cart {}", LOG_HEADER, cart);
      throw e;
    }
  }

  @Override
  public void deleteCartById(UUID cartId) {
    logger.info("{} Delete cart by id{}", LOG_HEADER, cartId);
    try {
      database.deleteCartEntityById(cartId);
    } catch (CartNotFoundException e) {
      logger.warn("{} Cart cannot be deleted since not found by id: {}", LOG_HEADER, cartId);
      throw e;
    } catch (Exception e) {
      logger.error("{} There was an error deleting a cart by id: {}", LOG_HEADER, cartId);
      throw e;
    }
  }

  @Override
  public void deleteAllUnusedCarts(int expiration) {
    logger.info("{} Delete all unused carts with expiration {}", LOG_HEADER, expiration);
    try {
      database.deleteAllUnusedCartEntities(expiration);
    } catch (Exception e) {
      logger.error(
          "{} There was an error deleting all unused carts with expiration {}",
          LOG_HEADER,
          expiration);
      throw e;
    }
  }

  @Override
  public Cart findCartById(UUID cartId) {
    logger.info("{} Get cart by id: {}", LOG_HEADER, cartId);
    try {

      return cartRepositoryMapper.asCart(database.findCartEntityById(cartId));
    } catch (CartNotFoundException e) {
      logger.warn("{} Cart not found by id: {}", LOG_HEADER, cartId);
      throw e;
    } catch (Exception e) {
      logger.error("{} There was an error finding a cart by id: {}", LOG_HEADER, cartId);
      throw e;
    }
  }
}
