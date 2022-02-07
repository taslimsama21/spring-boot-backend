package com.taslim.springbootbackend.controller;

import com.taslim.springbootbackend.model.Subscription;
import com.taslim.springbootbackend.service.SubscriptionService;
import com.taslim.springbootbackend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/sub/")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("add")
    public ResponseEntity<String> addSubscription(@RequestBody Subscription subscription){
        Logger.info(subscription.toString());
        return subscriptionService.addSubscription(subscription);
    }

    @GetMapping("cancel")
    public ResponseEntity<String> cancelSubscription(@Valid @PathVariable String sub_id){
        return subscriptionService.cancelSubscription(sub_id);
    }

    @GetMapping("resume")
    public ResponseEntity<String> resumeSubscription(@Valid @PathVariable String sub_id){
        return subscriptionService.resumeSubscription(sub_id);
    }
}
