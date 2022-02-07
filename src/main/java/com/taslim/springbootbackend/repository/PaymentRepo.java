package com.taslim.springbootbackend.repository;

import com.taslim.springbootbackend.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {
    Payment findByTxnId(String txnId);
}
