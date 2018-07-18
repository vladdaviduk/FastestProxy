package com.controller;

import com.entity.Proxy;
import com.repository.ProxyRepository;
import com.core.groovy.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("check")
class Controller {

    @Autowired
    ProxyRepository proxyRepository;


    @GetMapping
    HashMap<String, String> list() {

        long ping = 1000;

        HashMap<String, String> hash = new HashMap<>();

        List<Proxy> proxylist = proxyRepository.findAll();

        Check check = new Check();

        for(Proxy proxy : proxylist){

            long p = check.ping(proxy.getHost(), Integer.parseInt(proxy.getPort()));

            if(p < ping && p != 0) {
                ping = p;

                hash.clear();
                hash.put("host", proxy.getHost());
                hash.put("pingTimeout", Long.toString(p));

            }

            if(p != 0) {
                proxy.setPingTimeout(Long.toString(p));
                proxyRepository.save(proxy);
            }else{
                proxy.setPingTimeout("too much");
                proxyRepository.save(proxy);
            }
        }

        return hash;
    }
}
