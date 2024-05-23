package com.sandeep.rnd.repos;

import com.sandeep.rnd.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderDetail, Long> {

    @Query("select orderDetail from OrderDetail as orderDetail where orderDetail.brokerDetail.brokerId = :brokerId")
    List<OrderDetail> findByBrokerId(Long brokerId);
}
