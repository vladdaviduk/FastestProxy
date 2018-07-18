package com.controller;

import com.entity.Proxy;
import com.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class WebController {

    @Autowired
    ProxyRepository proxyRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {

        return "index";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String del() {

        List<Proxy> proxylist = proxyRepository.findAll();

        for(Proxy proxy : proxylist){
            proxy.setPingTimeout(null);
            proxyRepository.save(proxy);
        }

        return "index";
    }
}
