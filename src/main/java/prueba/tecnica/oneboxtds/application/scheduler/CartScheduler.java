package prueba.tecnica.oneboxtds.application.scheduler;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import prueba.tecnica.oneboxtds.domain.service.cart.ICartService;

@Service
public class CartScheduler {

  private final ICartService cartService;

  private static final String LOG_HEADER = "[APPLICATION][SCHEDULER][CART]";

  Logger logger = LoggerFactory.getLogger(CartScheduler.class);

  @Setter
  @Getter
  @Value("${cart.expiration}")
  private int expiration;

  public CartScheduler(ICartService cartService) {
    this.cartService = cartService;
  }

  @Scheduled(cron = "${cron.cart.cleanup.interval}")
  public void removeUnusedCarts() {
    logger.info("{} Remove unused carts during {} minutes", LOG_HEADER, expiration);
    cartService.deleteUnusedCart(expiration);
  }
}
