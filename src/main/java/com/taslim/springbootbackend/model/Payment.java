package com.taslim.springbootbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Payment")
public class Payment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String productInfo;
    @Column
    private Double amount;
    @Column
    private String paymentStatus;
    @Column
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column
    private String txnId;
    @Column
    private String mihpayId;
    @Column
    private Integer mode;
}
