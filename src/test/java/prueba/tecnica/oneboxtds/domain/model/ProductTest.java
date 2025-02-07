package prueba.tecnica.oneboxtds.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ProductTest {

  @Test
  public void productConstructorWithParameters() {
    Product product = new Product(UUID.randomUUID(), "description", new BigDecimal(10));

    assertNotNull(product.getId());
    assertNotNull(product.getDescription());
    assertNotNull(product.getAmount());
  }
}
