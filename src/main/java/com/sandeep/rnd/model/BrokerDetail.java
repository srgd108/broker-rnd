package com.sandeep.rnd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BrokerDetail {

    @Id
    private Integer brokerId;

    private String brokerName;
    private Double brokerLimit;
}
