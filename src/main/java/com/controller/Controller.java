package com.controller;

import com.collection.Proxy;
import com.core.GetProxy;
import com.core.groovy.Parse;
import com.repository.ProxyRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;


@RestController
//@EnableAutoConfiguration
@SpringBootApplication(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan("com")
public class Controller {

    @Autowired
    GetProxy getProxy;

    @Autowired
    ProxyRepository proxyRepository;

    @GetMapping
    @RequestMapping("/getProxy")
    JSONObject list() throws IOException {

       return getProxy.getFastestProxy();
    }

    @RequestMapping("/save")
    public String save() {

        Parse parse = new Parse();
        ArrayList<Proxy> socket = parse.parseProxy();

        for(Proxy proxy : socket){
            proxyRepository.save(proxy);
        }

        return "index";
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}
