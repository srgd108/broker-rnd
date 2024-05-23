package com.sandeep.rnd.services;

import com.sandeep.rnd.model.BrokerDetail;
import com.sandeep.rnd.repos.BrokerRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrokerService {

    @Autowired
    private BrokerRepo brokerRepo;

    private List<BrokerDetail> brokerDetailList;

    @PostConstruct
    private void initBrokerList(){
        brokerDetailList = new ArrayList<>(1);

        BrokerDetail brokerDetail = getBrokerDetail();
        brokerRepo.save(brokerDetail);
        brokerDetailList.add(brokerDetail);
    }

    private static BrokerDetail getBrokerDetail() {
        return BrokerDetail.builder().brokerId(123).brokerLimit(1000.00).brokerName("Sandeep").build();
    }

    public List<BrokerDetail> getBrokerDetailList(){
        return brokerDetailList;
    }
}