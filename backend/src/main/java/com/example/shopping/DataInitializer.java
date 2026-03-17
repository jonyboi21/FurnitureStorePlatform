package com.example.shopping;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.shopping.model.furniture;
import com.example.shopping.repositories.furnitureRepo;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    @ConditionalOnProperty(name = "app.seed-data", havingValue = "true")
    CommandLineRunner seedFurniture(furnitureRepo repository) {
        return args -> {
            repository.deleteAll();

            List<furniture> furnitureList = List.of(
                new furniture(null, "Modern Sofa", "Living Room", 499.99, 10, 1001,
                        "A comfortable modern sofa.",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxXvUSLIVX-59Vqy0gUKl7u-f2_HXYV4tWCQ&size=1280x720", null),

                new furniture(null, "Dining Table", "Dining", 299.99, 5, 1002,
                        "A wooden dining table.",
                        "https://www.dropbox.com/scl/fi/zjz7fxr44ok8bsrmo19rf/Dining_Set.webp?rlkey=ze4vi69zqfvl5d5lyhi321lkm&st=xv31xutr&raw=1", null),

                new furniture(null, "Office Chair", "Office", 129.99, 20, 1003,
                        "Ergonomic office chair with lumbar support.",
                        "https://www.dropbox.com/scl/fi/ndm54qhtu116sy6rjkcon/vari-task-chair_400663_black_office.jpg?rlkey=5ejem20ajbtkkj2cagmchtn6b&st=w03okeb6&raw=1", null)
            );

            repository.saveAll(furnitureList);
        };
    }
}
