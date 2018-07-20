package com.controller;

import com.core.groovy.Parse;
import com.collection.Proxy;
import com.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;

@Controller
class StartPageController {

    @Autowired
    ProxyRepository proxyRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save() {

        Parse parse = new Parse();
        ArrayList<Proxy> socket = parse.parseProxy();

        for(Proxy proxy : socket){
            proxyRepository.save(proxy);
        }

        return "index";
    }
}
