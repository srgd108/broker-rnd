package com.sandeep.rnd.controllers;

import com.sandeep.rnd.exceptions.IncorrectOrderException;
import com.sandeep.rnd.model.BrokerDetail;
import com.sandeep.rnd.model.OrderDetail;
import com.sandeep.rnd.repos.BrokerRepo;
import com.sandeep.rnd.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    BrokerRepo brokerRepo;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrders() {
        return ResponseEntity.ok(orderRepo.findAll());
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDetail> getOrderDetailsForOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderRepo.findById(orderId).orElseThrow(() -> new IncorrectOrderException(HttpStatus.NOT_FOUND, "Wrong Order Id")));
    }

    @PostMapping
    public OrderDetail postOrder(@RequestBody OrderDetail orderDetail) {
        return orderRepo.save(orderDetail);
    }

    // Broker Methods

    @GetMapping("/brokers")
    public ResponseEntity<List<BrokerDetail>> getAllBrokers() {
        return ResponseEntity.ok(brokerRepo.findAll());
    }

    @GetMapping("/brokers/{brokerId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsForBrokerId(@PathVariable Long brokerId) {
        return ResponseEntity.ok(orderRepo.findByBrokerId(brokerId));
    }

}
