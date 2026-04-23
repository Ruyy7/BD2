package unlp.info.bd2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import unlp.info.bd2.repositories.*;
import unlp.info.bd2.services.*;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public ToursService createService(ItemServiceRepositoryInterface itemServiceRepository,
            PurchaseRepositoryInterface purchaseRepository, ReviewRepositoryInterface reviewRepository,
            RouteRepositoryInterface routeRepository, ServiceRepositoryInterface serviceRepository,
            StopRepositoryInterface stopRepository, SupplierRepositoryInterface supplierRepository,
            UserRepositoryInterface userRepository) {
        return new TourServiceImpl(itemServiceRepository, purchaseRepository, reviewRepository, routeRepository, serviceRepository, stopRepository, supplierRepository, userRepository);
    }

    // @Bean
    // @Primary
    // public ToursRepository createRepository() {
    //     return new ToursRepositoryImpl();
    // }
}
