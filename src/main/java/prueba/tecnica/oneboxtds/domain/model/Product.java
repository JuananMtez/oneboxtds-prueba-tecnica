package prueba.tecnica.oneboxtds.domain.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {

  private UUID id;
  private String description;
  private BigDecimal amount;
}
