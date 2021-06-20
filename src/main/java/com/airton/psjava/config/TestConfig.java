package com.airton.psjava.config;

import com.airton.psjava.entities.Product;
import com.airton.psjava.entities.Shopcart;
import com.airton.psjava.repository.ProductRepository;
import com.airton.psjava.repository.ShopcartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopcartRepository shopcartRepository;

    @Override
    public void run(String... args) throws Exception {

        Product p1 = new Product(null, "Super Mario Odyssey",new BigDecimal("197.88"), Short.parseShort("200"), "super-mario-odyssey.png");
        Product p2 = new Product(null, "Call Of Duty Infinite Warfare",new BigDecimal("49.99"), Short.parseShort("80"), "sCall Of Duty Infinite Warfare.png");
        Product p3 = new Product(null, "The Witcher III Wild Hunt",new BigDecimal("119.5"), Short.parseShort("250"), "The Witcher III Wild Hunt.png");
        Product p4 = new Product(null, "Mass Effect Legendary Edition",new BigDecimal("150.0"), Short.parseShort("270"), "Mass Effect Legendary Edition.png");
        Product p5 = new Product(null, "Drift 21",new BigDecimal("30.0"), Short.parseShort("130"), "Drift 21.png");
        Product p6 = new Product(null, "FIFA21",new BigDecimal("290.7"), Short.parseShort("160"), "FIFA21.png");

        productRepository.saveAll((Arrays.asList(p1,p2,p3,p4,p5,p6)));

        Shopcart shop1 = new Shopcart(null, LocalDateTime.now());
        Shopcart shop2 = new Shopcart(null, LocalDateTime.now());
        Shopcart shop3 = new Shopcart(null, LocalDateTime.now());

        shopcartRepository.saveAll((Arrays.asList(shop1,shop2,shop3)));



    }

}
