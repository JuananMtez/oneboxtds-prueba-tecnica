package prueba.tecnica.oneboxtds.application.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import prueba.tecnica.oneboxtds.domain.service.cart.ICartService;

@Service
public class CartScheduler {

  private ICartService cartService;

  private static final String LOG_HEADER = "[APPLICATION][SCHEDULER][CART]";

  Logger logger = LoggerFactory.getLogger(CartScheduler.class);

  public CartScheduler(ICartService cartService) {
    this.cartService = cartService;
  }

  @Scheduled(cron = "0 0/5 * * * ?")
  public void removeUnusedCarts() {
    logger.info("{} Remove unused carts", LOG_HEADER);
    cartService.deleteUnusedCart();
  }
}
