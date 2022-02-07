package com.taslim.springbootbackend.controller;

import com.taslim.springbootbackend.model.PaymentCallback;
import com.taslim.springbootbackend.model.PaymentDetail;
import com.taslim.springbootbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payment/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping(path = "/payment-details")
    public @ResponseBody
    PaymentDetail proceedPayment(@RequestBody PaymentDetail paymentDetail){
        return paymentService.proceedPayment(paymentDetail);
    }

    @PostMapping(path = "/payment-response")
    public ResponseEntity<String> payuCallback(@RequestParam String mihpayid, @RequestParam String status, @RequestParam Integer mode, @RequestParam String txnid, @RequestParam String hash){
        PaymentCallback paymentCallback = new PaymentCallback();
        paymentCallback.setMihpayid(mihpayid);
        paymentCallback.setTxnid(txnid);
        paymentCallback.setMode(mode);
        paymentCallback.setHash(hash);
        paymentCallback.setStatus(status);
        return paymentService.payuCallback(paymentCallback);
    }

}
