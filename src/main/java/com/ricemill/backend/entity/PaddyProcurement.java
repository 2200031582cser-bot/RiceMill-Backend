package com.ricemill.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class PaddyProcurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    private String date;

    private String farmerName;

    private String procurementYear;

    private String seasonType;

    private String riceType;

    private Double quantity;

    private Integer numberOfBags;

    private Double pricePerQuintal;

    private Double grossWeight;

    private Double moistureCutting;

    private Double hamaliCharges;

    private Double weighBridgeCharges;

    private Double cashCutting;

    private Double netWeight;

    private Double finalAmount;
}