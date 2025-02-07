package prueba.tecnica.oneboxtds.domain.exception;

public class CartNotFoundException extends RuntimeException {
  public CartNotFoundException(String message) {
    super(message);
  }
}
