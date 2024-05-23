package com.sandeep.rnd.services;

import com.sandeep.rnd.model.BrokerDetail;
import com.sandeep.rnd.model.OrderDetail;
import com.sandeep.rnd.model.enums.OrderSide;
import com.sandeep.rnd.model.enums.OrderStatus;
import com.sandeep.rnd.model.enums.StockSymbol;
import com.sandeep.rnd.repos.BrokerRepo;
import com.sandeep.rnd.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class OrderGenerator {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private BrokerRepo brokerRepo;

    @Autowired
    private BrokerService brokerService;

    private BrokerDetail getBrokerDetail(){
        return brokerService.getBrokerDetailList().get(0);
    }

    public String generateBuyOrder() {
        LongStream.rangeClosed(1, 3).forEach(i -> {
            BrokerDetail brokerDetail = getBrokerDetail();
            OrderDetail orderDetail = OrderDetail.builder().orderQuantity(i)
                    .orderPrice(BigDecimal.valueOf(new Random().nextDouble(50, 55)).setScale(1, RoundingMode.DOWN))
                    .brokerDetail(brokerDetail).stockSymbol(StockSymbol.APPL).orderStatus(OrderStatus.NEW)
                    .orderSide(OrderSide.BUY).build();
            orderRepo.save(orderDetail);
        });
        return "Sample Buy-Orders added";
    }

    public String generateSellOrder() {
        LongStream.rangeClosed(1, 3).forEach(i -> {
            BrokerDetail brokerDetail = getBrokerDetail();
            OrderDetail orderDetail = OrderDetail.builder().orderQuantity(i)
                    .orderPrice(BigDecimal.valueOf(new Random().nextDouble(50, 55)).setScale(1, RoundingMode.DOWN))
                    .brokerDetail(brokerDetail).stockSymbol(StockSymbol.APPL).orderStatus(OrderStatus.NEW)
                    .orderSide(OrderSide.SELL).build();
            orderRepo.save(orderDetail);
        });
        return "Sample Sell-Orders added";
    }
}
