package prueba.tecnica.oneboxtds.infrastructure.repository.product.entity;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductEntity {
  private UUID id;
  private String description;
  private BigDecimal amount;
}
