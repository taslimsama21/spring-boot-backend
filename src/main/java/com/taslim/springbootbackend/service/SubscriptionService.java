package com.taslim.springbootbackend.service;

import com.taslim.springbootbackend.model.Subscription;
import com.taslim.springbootbackend.repository.SubscriptionRepository;
import com.taslim.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public ResponseEntity<String> addSubscription(Subscription subscription){

        if (subscriptionRepository.existsByUserId(subscription.getUserId())){
            return ResponseEntity.ok("You already have subscription!!");
        }

        subscriptionRepository.save(subscription);

       if (subscriptionRepository.existsByUserId(subscription.getUserId())){
           return ResponseEntity.ok("You have subscribed successfully!!");
       }
        else
       {
           return new ResponseEntity("Error occurred,not subscribed!!", HttpStatus.NOT_IMPLEMENTED);
       }
    }

    public ResponseEntity<String> cancelSubscription(String sub_id){

          Optional<Subscription> subscription = subscriptionRepository.findById(Long.parseLong(sub_id));

          if (Objects.isNull(subscription)){
              return new ResponseEntity("Error occurred,subscription not cancelled!!", HttpStatus.NOT_IMPLEMENTED);
          } else
          {
              Subscription subscriptionData = subscriptionRepository.getById(Long.parseLong(sub_id));
              subscriptionData.setActive(false);
              return ResponseEntity.ok("Your subscription is cancelled!!");
          }
    }

    public ResponseEntity<String> resumeSubscription(String sub_id){

        Optional<Subscription> subscription = subscriptionRepository.findById(Long.parseLong(sub_id));

        if (Objects.isNull(subscription)){
            return new ResponseEntity("Error occurred,subscription not resumed!!", HttpStatus.NOT_IMPLEMENTED);
        } else
        {
            Subscription subscriptionData = subscriptionRepository.getById(Long.parseLong(sub_id));
            subscriptionData.setActive(true);
            return ResponseEntity.ok("Your have resumed subscription!!");
        }

    }

}
