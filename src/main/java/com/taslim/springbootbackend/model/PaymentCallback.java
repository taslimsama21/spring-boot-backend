package com.taslim.springbootbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentCallback {

    private String txnid;
    private String mihpayid;
    private Integer mode;
    private String status;
    private String hash;
}
