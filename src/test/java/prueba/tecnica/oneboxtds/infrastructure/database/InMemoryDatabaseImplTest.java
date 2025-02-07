package prueba.tecnica.oneboxtds.infrastructure.database;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prueba.tecnica.oneboxtds.domain.exception.CartAlreadyExistsException;
import prueba.tecnica.oneboxtds.domain.exception.CartNotFoundException;
import prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity.CartEntity;
import prueba.tecnica.oneboxtds.infrastructure.repository.database.InMemoryDatabaseImpl;
import prueba.tecnica.oneboxtds.infrastructure.repository.product.entity.ProductEntity;

public class InMemoryDatabaseImplTest {

  private InMemoryDatabaseImpl inMemoryDatabase;

  private final ProductEntity productEntity =
      new ProductEntity(UUID.randomUUID(), "product", BigDecimal.valueOf(0));

  private final CartEntity cartEntity =
      new CartEntity(
          UUID.randomUUID(), List.of(productEntity), LocalDateTime.now(), LocalDateTime.now());

  @BeforeEach
  void setUp() {
    inMemoryDatabase = new InMemoryDatabaseImpl();
    inMemoryDatabase.saveCartEntity(cartEntity);
  }

  @Test
  void findAllCartEntities_Success() {

    List<CartEntity> carts = inMemoryDatabase.findAllCartEntities();

    assertNotNull(carts);
    assertEquals(1, carts.size());
    assertEquals(cartEntity.getId(), carts.get(0).getId());
    assertEquals(productEntity.getId(), carts.get(0).getProducts().get(0).getId());
    assertEquals(
        productEntity.getDescription(), carts.get(0).getProducts().get(0).getDescription());
    assertEquals(productEntity.getAmount(), carts.get(0).getProducts().get(0).getAmount());
  }

  @Test
  void findCartEntityById_Success() {

    CartEntity cartDb = inMemoryDatabase.findCartEntityById(cartEntity.getId());

    assertNotNull(cartDb);

    assertEquals(cartEntity.getId(), cartDb.getId());
    assertEquals(cartEntity.getCreationDate(), cartDb.getCreationDate());
    assertEquals(cartEntity.getModificationDate(), cartDb.getModificationDate());

    assertEquals(productEntity.getId(), cartDb.getProducts().get(0).getId());
    assertEquals(productEntity.getDescription(), cartDb.getProducts().get(0).getDescription());
    assertEquals(productEntity.getAmount(), cartDb.getProducts().get(0).getAmount());
  }

  @Test
  void findCartEntityById_Throws_NotFound() {

    CartNotFoundException exception =
        assertThrows(
            CartNotFoundException.class,
            () -> inMemoryDatabase.findCartEntityById(UUID.randomUUID()));

    assertEquals("Cart not found in database", exception.getMessage());
  }

  @Test
  void saveCart_Success() {

    CartEntity cartEntityNew =
        new CartEntity(
            UUID.randomUUID(), List.of(productEntity), LocalDateTime.now(), LocalDateTime.now());
    CartEntity cartDb = inMemoryDatabase.saveCartEntity(cartEntityNew);

    assertNotNull(cartDb);

    assertEquals(cartEntityNew.getId(), cartDb.getId());
    assertEquals(cartEntityNew.getCreationDate(), cartDb.getCreationDate());
    assertEquals(cartEntityNew.getModificationDate(), cartDb.getModificationDate());
    assertEquals(cartEntityNew.getProducts().get(0).getId(), cartDb.getProducts().get(0).getId());
    assertEquals(
        cartEntityNew.getProducts().get(0).getDescription(),
        cartDb.getProducts().get(0).getDescription());
    assertEquals(
        cartEntityNew.getProducts().get(0).getAmount(), cartDb.getProducts().get(0).getAmount());
  }

  @Test
  void saveCartEntity_Throws_AlreadyExists() {

    CartAlreadyExistsException exception =
        assertThrows(
            CartAlreadyExistsException.class, () -> inMemoryDatabase.saveCartEntity(cartEntity));

    assertEquals("Cart already exists in database", exception.getMessage());
  }

  @Test
  void updateCartEntity_Success() {

    CartEntity cartDb = inMemoryDatabase.updateCartEntity(cartEntity);

    assertNotNull(cartDb);

    assertEquals(cartEntity.getId(), cartDb.getId());
    assertEquals(cartEntity.getCreationDate(), cartDb.getCreationDate());
    assertEquals(cartEntity.getModificationDate(), cartDb.getModificationDate());

    assertEquals(cartEntity.getProducts().get(0).getId(), cartDb.getProducts().get(0).getId());
    assertEquals(
        cartEntity.getProducts().get(0).getDescription(),
        cartDb.getProducts().get(0).getDescription());
    assertEquals(
        cartEntity.getProducts().get(0).getAmount(), cartDb.getProducts().get(0).getAmount());
  }

  @Test
  void updateCartEntity_Throws_NotFound() {

    CartEntity cartEntityNew =
        new CartEntity(
            UUID.randomUUID(), List.of(productEntity), LocalDateTime.now(), LocalDateTime.now());

    CartNotFoundException exception =
        assertThrows(
            CartNotFoundException.class, () -> inMemoryDatabase.updateCartEntity(cartEntityNew));

    assertEquals("Cart not found in database", exception.getMessage());
  }

  @Test
  void deleteCartEntityById_Success() {

    inMemoryDatabase.deleteCartEntityById(cartEntity.getId());

    CartNotFoundException exception =
        assertThrows(
            CartNotFoundException.class,
            () -> inMemoryDatabase.findCartEntityById(cartEntity.getId()));

    assertEquals("Cart not found in database", exception.getMessage());
  }

  @Test
  void deleteCartEntityById_NotFound() {

    CartNotFoundException exception =
        assertThrows(
            CartNotFoundException.class,
            () -> inMemoryDatabase.deleteCartEntityById(UUID.randomUUID()));

    assertEquals("Cart not found in database", exception.getMessage());
  }
}
