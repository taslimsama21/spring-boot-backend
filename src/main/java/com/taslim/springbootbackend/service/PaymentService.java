package com.taslim.springbootbackend.service;

import com.taslim.springbootbackend.model.Payment;
import com.taslim.springbootbackend.model.PaymentCallback;
import com.taslim.springbootbackend.model.PaymentDetail;
import com.taslim.springbootbackend.repository.PaymentRepo;
import com.taslim.springbootbackend.repository.PaymentRepository;
import com.taslim.springbootbackend.utils.AppConstants;
import com.taslim.springbootbackend.utils.PaymentStatus;
import com.taslim.springbootbackend.utils.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService implements PaymentRepository {


    @Autowired
    private PaymentRepo paymentRepository;


    @Override
    public PaymentDetail proceedPayment(PaymentDetail paymentDetail) {
        PaymentUtil paymentUtil = new PaymentUtil();
        paymentDetail = paymentUtil.populatePaymentDetail(paymentDetail);
        savePaymentDetail(paymentDetail);
        return paymentDetail;
    }


    @Override
    public ResponseEntity<String> payuCallback(PaymentCallback paymentResponse) {
        String msg = "Transaction failed.";
        Payment payment = paymentRepository.findByTxnId(paymentResponse.getTxnid());
        if(payment != null) {
            //TODO validate the hash
            String paymentStatus = null;
            if(paymentResponse.getStatus().equals("failure")){
                paymentStatus = AppConstants.PAYMENT_STATUS_FAILED;
            }else if(paymentResponse.getStatus().equals("success")) {
                paymentStatus = AppConstants.PAYMENT_STATUS_SUCCESS;
                msg = "Transaction success";
            }
            payment.setPaymentStatus(paymentStatus);
            payment.setMihpayId(paymentResponse.getMihpayid());
            payment.setMode(paymentResponse.getMode());
            paymentRepository.save(payment);
        }
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    private void savePaymentDetail(PaymentDetail paymentDetail) {
        Payment payment = new Payment();
        payment.setAmount(Double.parseDouble(paymentDetail.getAmount()));
        payment.setEmail(paymentDetail.getEmail());
        payment.setName(paymentDetail.getName());
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(AppConstants.PAYMENT_STATUS_PENDING);
        payment.setPhone(paymentDetail.getPhone());
        payment.setProductInfo(paymentDetail.getProductInfo());
        payment.setTxnId(paymentDetail.getTxnId());
        paymentRepository.save(payment);
    }
}
