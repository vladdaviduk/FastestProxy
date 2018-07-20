package com.controller;

import com.core.GetProxy;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("getProxy")
class GetProxyController {

    @Autowired
    GetProxy getProxy;

    @GetMapping
    JSONObject list() throws IOException {

       return getProxy.getFastestProxy();
    }
}
