package prueba.tecnica.oneboxtds.infrastructure.repository.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prueba.tecnica.oneboxtds.domain.database.IInMemoryDatabase;
import prueba.tecnica.oneboxtds.domain.exception.CartAlreadyExistsException;
import prueba.tecnica.oneboxtds.domain.exception.CartNotFoundException;
import prueba.tecnica.oneboxtds.domain.model.Cart;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.mapper.ICartRepositoryMapper;

@ExtendWith(MockitoExtension.class)
public class CartRepositoryImplTest {

  @InjectMocks private CartRepositoryImpl cartRepositoryImpl;

  @Mock private IInMemoryDatabase database;

  @Mock private ICartRepositoryMapper cartRepositoryMapper;

  @Test
  void findAllCarts_Success() {

    List<CartEntity> cartEntityList = List.of(createCartEntity());
    List<Cart> cartList = List.of(createCart());

    when(database.findAllCartEntities()).thenReturn(cartEntityList);
    when(cartRepositoryMapper.asCartList(cartEntityList)).thenReturn(cartList);

    List<Cart> result = cartRepositoryImpl.findAllCarts();

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(database, times(1)).findAllCartEntities();
    verify(cartRepositoryMapper, times(1)).asCartList(cartEntityList);
  }

  @Test
  void findAllCarts_ThrowsException() {

    when(database.findAllCartEntities()).thenThrow(new RuntimeException());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartRepositoryImpl.findAllCarts());
    assertNotNull(exception);

    verify(database, times(1)).findAllCartEntities();
  }

  @Test
  void saveCart_Success() {

    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(cartRepositoryMapper.asCartEntity(cart)).thenReturn(cartEntity);
    when(database.saveCartEntity(cartEntity)).thenReturn(cartEntity);
    when(cartRepositoryMapper.asCart(cartEntity)).thenReturn(cart);

    Cart result = cartRepositoryImpl.saveCart(cart);

    assertNotNull(result);
    verify(cartRepositoryMapper, times(1)).asCartEntity(cart);
    verify(database, times(1)).saveCartEntity(cartEntity);
    verify(cartRepositoryMapper, times(1)).asCart(cartEntity);
  }

  @Test
  void saveCart_ThrowsAlreadyExists() {

    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(cartRepositoryMapper.asCartEntity(cart)).thenReturn(cartEntity);
    when(database.saveCartEntity(cartEntity))
        .thenThrow(new CartAlreadyExistsException("Cart already exists in database"));

    CartAlreadyExistsException exception =
        assertThrows(CartAlreadyExistsException.class, () -> cartRepositoryImpl.saveCart(cart));
    assertNotNull(exception);
    verify(cartRepositoryMapper, times(1)).asCartEntity(cart);
    verify(database, times(1)).saveCartEntity(cartEntity);
  }

  @Test
  void saveCart_ThrowsException() {

    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(cartRepositoryMapper.asCartEntity(cart)).thenReturn(cartEntity);
    when(database.saveCartEntity(cartEntity)).thenThrow(new RuntimeException());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartRepositoryImpl.saveCart(cart));
    assertNotNull(exception);
    verify(cartRepositoryMapper, times(1)).asCartEntity(cart);
    verify(database, times(1)).saveCartEntity(cartEntity);
  }

  @Test
  void updateCart_Success() {

    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(cartRepositoryMapper.asCartEntity(cart)).thenReturn(cartEntity);
    when(database.updateCartEntity(cartEntity)).thenReturn(cartEntity);
    when(cartRepositoryMapper.asCart(cartEntity)).thenReturn(cart);

    Cart result = cartRepositoryImpl.updateCart(cart);

    assertNotNull(result);
    verify(cartRepositoryMapper, times(1)).asCartEntity(cart);
    verify(database, times(1)).updateCartEntity(cartEntity);
    verify(cartRepositoryMapper, times(1)).asCart(cartEntity);
  }

  @Test
  void updateCart_ThrowsNotFound() {

    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(cartRepositoryMapper.asCartEntity(cart)).thenReturn(cartEntity);
    when(database.updateCartEntity(cartEntity))
        .thenThrow(new CartNotFoundException("Cart not found in database"));

    CartNotFoundException exception =
        assertThrows(CartNotFoundException.class, () -> cartRepositoryImpl.updateCart(cart));
    assertNotNull(exception);
    verify(cartRepositoryMapper, times(1)).asCartEntity(cart);
    verify(database, times(1)).updateCartEntity(cartEntity);
  }

  @Test
  void updateCart_ThrowsException() {

    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(cartRepositoryMapper.asCartEntity(cart)).thenReturn(cartEntity);
    when(database.updateCartEntity(cartEntity)).thenThrow(new RuntimeException());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartRepositoryImpl.updateCart(cart));
    assertNotNull(exception);
    verify(cartRepositoryMapper, times(1)).asCartEntity(cart);
    verify(database, times(1)).updateCartEntity(cartEntity);
  }

  @Test
  void findCartById_Success() {
    UUID id = UUID.randomUUID();
    CartEntity cartEntity = createCartEntity();
    Cart cart = createCart();

    when(database.findCartEntityById(id)).thenReturn(cartEntity);
    when(cartRepositoryMapper.asCart(cartEntity)).thenReturn(cart);

    Cart result = cartRepositoryImpl.findCartById(id);

    assertNotNull(result);
    verify(database, times(1)).findCartEntityById(id);
    verify(cartRepositoryMapper, times(1)).asCart(cartEntity);
  }

  @Test
  void findCartById_ThrowsNotFound() {
    UUID id = UUID.randomUUID();

    when(database.findCartEntityById(id))
        .thenThrow(new CartNotFoundException("Cart not found in database"));

    CartNotFoundException exception =
        assertThrows(CartNotFoundException.class, () -> cartRepositoryImpl.findCartById(id));
    assertNotNull(exception);
    verify(database, times(1)).findCartEntityById(id);
  }

  @Test
  void findCartById_ThrowsException() {

    UUID id = UUID.randomUUID();

    when(database.findCartEntityById(id)).thenThrow(new RuntimeException());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartRepositoryImpl.findCartById(id));
    assertNotNull(exception);
    verify(database, times(1)).findCartEntityById(id);
  }

  @Test
  void deleteCartById_Success() {
    UUID id = UUID.randomUUID();

    cartRepositoryImpl.deleteCartById(id);

    verify(database, times(1)).deleteCartEntityById(id);
  }

  @Test
  void deleteCartById_ThrowsException() {

    UUID id = UUID.randomUUID();

    doThrow(new RuntimeException()).when(database).deleteCartEntityById(id);

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartRepositoryImpl.deleteCartById(id));
    assertNotNull(exception);
    verify(database, times(1)).deleteCartEntityById(id);
  }

  @Test
  void deleteCartById_NotFound() {

    UUID id = UUID.randomUUID();

    doThrow(new CartNotFoundException("Cart not found")).when(database).deleteCartEntityById(id);

    CartNotFoundException exception =
        assertThrows(CartNotFoundException.class, () -> cartRepositoryImpl.deleteCartById(id));
    assertNotNull(exception);
    verify(database, times(1)).deleteCartEntityById(id);
  }

  @Test
  void deleteAllUnusedCarts_Success() {
    int expiration = 10;

    database.deleteAllUnusedCartEntities(expiration);

    verify(database, times(1)).deleteAllUnusedCartEntities(expiration);
  }

  @Test
  void findAllUnusedCarts_ThrowsException() {

    int expiration = 10;

    doThrow(new RuntimeException()).when(database).deleteAllUnusedCartEntities(expiration);

    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> cartRepositoryImpl.deleteAllUnusedCarts(expiration));

    assertNotNull(exception);
    verify(database, times(1)).deleteAllUnusedCartEntities(expiration);
  }

  private CartEntity createCartEntity() {
    return new CartEntity(UUID.randomUUID(), List.of(), LocalDateTime.now(), LocalDateTime.now());
  }

  private Cart createCart() {
    return new Cart();
  }
}
