package com.sandeep.rnd.model;

import com.sandeep.rnd.model.enums.OrderSide;
import com.sandeep.rnd.model.enums.OrderStatus;
import com.sandeep.rnd.model.enums.StockSymbol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private StockSymbol stockSymbol;
    private OrderSide orderSide;
    private Long orderQuantity;
    private BigDecimal orderPrice;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "brokerId")
    private BrokerDetail brokerDetail;
}
