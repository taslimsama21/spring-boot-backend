package com.taslim.springbootbackend.repository;

import com.taslim.springbootbackend.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    Boolean existsByUserId(String userID);
}
