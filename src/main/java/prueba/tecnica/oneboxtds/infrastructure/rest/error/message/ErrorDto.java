package prueba.tecnica.oneboxtds.infrastructure.rest.error.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import prueba.tecnica.oneboxtds.domain.exception.ErrorEnum;

@Getter
@AllArgsConstructor
public class ErrorDto {
  private String message;
  private ErrorEnum error;
}
