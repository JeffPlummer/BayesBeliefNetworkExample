package com.plummersmind.bayesexample.services;

import java.util.List;

import org.granite.messaging.service.annotations.RemoteDestination;

import com.plummersmind.bayesexample.entities.Welcome;


@RemoteDestination
public interface WelcomeService {

    public Welcome hello(String name);
    
    public List<Welcome> findAll();
}
