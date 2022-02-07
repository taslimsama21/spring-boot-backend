package com.taslim.springbootbackend.repository;

import com.taslim.springbootbackend.model.PaymentCallback;
import com.taslim.springbootbackend.model.PaymentDetail;
import org.springframework.http.ResponseEntity;

public interface PaymentRepository {
    PaymentDetail proceedPayment(PaymentDetail paymentDetail);

    ResponseEntity<String> payuCallback(PaymentCallback paymentResponse);
}
