package prueba.tecnica.oneboxtds.infrastructure.repository.cart.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import prueba.tecnica.oneboxtds.infrastructure.repository.product.entity.ProductEntity;

public class CartEntityTest {

  @Test
  public void cartEntityConstructorWithSpecificParameters() {
    UUID id = UUID.randomUUID();
    ProductEntity product1 =
        new ProductEntity(UUID.randomUUID(), "Product 1", new BigDecimal("10.0"));
    ProductEntity product2 =
        new ProductEntity(UUID.randomUUID(), "Product 2", new BigDecimal("10.0"));
    List<ProductEntity> products = Arrays.asList(product1, product2);
    LocalDateTime creationDate = LocalDateTime.of(2025, 2, 7, 10, 0, 0, 0);
    LocalDateTime modificationDate = LocalDateTime.of(2025, 2, 7, 10, 30, 0, 0);

    CartEntity cartEntity = new CartEntity(id, products, creationDate, modificationDate);

    assertEquals(id, cartEntity.getId());

    assertNotNull(cartEntity.getProducts());
    assertEquals(2, cartEntity.getProducts().size());

    assertNotNull(cartEntity.getCreationDate());
    assertNotNull(cartEntity.getModificationDate());

    assertEquals(creationDate, cartEntity.getCreationDate());
    assertEquals(modificationDate, cartEntity.getModificationDate());
  }
}
