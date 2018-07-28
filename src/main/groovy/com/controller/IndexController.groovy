package com.controller;

import com.service.GetProxy;
import com.service.ParseProxy;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @Autowired
    GetProxy getProxy;

    @Autowired
    ParseProxy parseProxy;

    @RequestMapping("/getProxy")
    JSONObject list() throws IOException {

       return getProxy.getFastestProxy();
    }

    @RequestMapping("/run/proxy-parser")
    String save() {

        parseProxy.save();

        return "success";
    }

    @RequestMapping("/")
    String home() {
        return "hello";
    }
}
