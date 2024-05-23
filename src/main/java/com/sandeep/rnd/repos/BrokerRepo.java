package com.sandeep.rnd.repos;

import com.sandeep.rnd.model.BrokerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepo extends JpaRepository<BrokerDetail, Long> {
}
