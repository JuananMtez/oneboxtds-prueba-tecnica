package prueba.tecnica.oneboxtds.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {

  private Cart cart;
  private Product product1;
  private Product product2;
  private Product product3;

  @BeforeEach
  public void setUp() {

    product1 = new Product(UUID.randomUUID(), "Product 1", new BigDecimal("10.0"));
    product2 = new Product(UUID.randomUUID(), "Product 2", new BigDecimal("15.0"));
    product3 = new Product(UUID.randomUUID(), "Product 3", new BigDecimal("15.0"));

    cart =
        new Cart(
            UUID.randomUUID(),
            Arrays.asList(product1, product2, product3),
            LocalDateTime.now(),
            LocalDateTime.now());
  }

  @Test
  public void cartConstructorWithoutParameters() {
    Cart cart = new Cart();

    assertNotNull(cart.getId());

    assertNotNull(cart.getProducts());
    assertTrue(cart.getProducts().isEmpty());

    assertNotNull(cart.getCreationDate());
    assertNotNull(cart.getModificationDate());
  }

  @Test
  public void cartConstructorWithProducts() {
    Product product1 = new Product(UUID.randomUUID(), "Product 1", new BigDecimal("10.0"));
    Product product2 = new Product(UUID.randomUUID(), "Product 2", new BigDecimal("10.0"));
    List<Product> products = Arrays.asList(product1, product2);

    Cart cart = new Cart(products);

    assertNotNull(cart.getId());

    assertNotNull(cart.getProducts());
    assertEquals(2, cart.getProducts().size());

    assertNotNull(cart.getCreationDate());
    assertNotNull(cart.getModificationDate());
  }

  @Test
  public void cartConstructorWithSpecificParameters() {
    UUID id = UUID.randomUUID();
    Product product1 = new Product(UUID.randomUUID(), "Product 1", new BigDecimal("10.0"));
    Product product2 = new Product(UUID.randomUUID(), "Product 2", new BigDecimal("10.0"));
    List<Product> products = Arrays.asList(product1, product2);
    LocalDateTime creationDate = LocalDateTime.of(2025, 2, 7, 10, 0, 0, 0);
    LocalDateTime modificationDate = LocalDateTime.of(2025, 2, 7, 10, 30, 0, 0);

    Cart cart = new Cart(id, products, creationDate, modificationDate);

    assertEquals(id, cart.getId());

    assertNotNull(cart.getProducts());
    assertEquals(2, cart.getProducts().size());

    assertNotNull(cart.getCreationDate());
    assertNotNull(cart.getModificationDate());

    assertEquals(creationDate, cart.getCreationDate());
    assertEquals(modificationDate, cart.getModificationDate());
  }

  @Test
  public void removeProductsByIds_RemovesMatchingProducts() {

    List<UUID> productIdsToRemove = Arrays.asList(product1.getId(), product3.getId());

    cart.removeProductsByIds(productIdsToRemove);

    assertFalse(cart.getProducts().contains(product1));
    assertFalse(cart.getProducts().contains(product3));
    assertTrue(cart.getProducts().contains(product2));
  }

  @Test
  public void addProducts_AddsNewProducts() {
    Product newProduct1 = new Product(UUID.randomUUID(), "Product 4", new BigDecimal("15.0"));
    Product newProduct2 = new Product(UUID.randomUUID(), "Product 5", new BigDecimal("15.0"));

    cart.addProducts(Arrays.asList(newProduct1, newProduct2));

    assertTrue(cart.getProducts().contains(newProduct1));
    assertTrue(cart.getProducts().contains(newProduct2));
  }
}
