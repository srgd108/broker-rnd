package com.sandeep.rnd;

import com.sandeep.rnd.services.OrderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class RndApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RndApplication.class, args);
    }

    @Autowired
    OrderGenerator orderGenerator;

    @Override
    public void run(String... args) {

        CompletableFuture<String> sellOrder = CompletableFuture.supplyAsync(() -> orderGenerator.generateSellOrder());

        CompletableFuture<String> buyOrder = CompletableFuture.supplyAsync(() -> orderGenerator.generateBuyOrder());

        sellOrder.thenCombineAsync(buyOrder, (s, b) -> s + " and " + b).thenAccept(System.out::println).join();
    }


}
