import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Leonardo Galati on 07/09/2017.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.salestaxes"})
public class SalesTaxesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesTaxesApplication.class, args);
    }

}
