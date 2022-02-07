package com.taslim.springbootbackend.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "sub_scription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "sub_scription_date")
    private Long subScriptionDate = System.currentTimeMillis() / 1000;

    @Column(name = "is_active")
    private boolean isActive = true;


}
