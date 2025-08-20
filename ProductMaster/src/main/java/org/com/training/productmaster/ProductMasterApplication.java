package org.com.training.productmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProductMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMasterApplication.class, args);
    }

}
