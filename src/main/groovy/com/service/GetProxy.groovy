package com.service;

import com.service.collection.Proxy;
import com.service.repository.ProxyRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GetProxy {

    @Autowired
    ProxyRepository proxyRepository;

    JSONObject getFastestProxy() {

        Proxy proxy = proxyRepository.findFirstByProxyInuseIsFalseAndPingSuccessIsTrueOrderByPingTimeoutAsc();
        proxy.setProxyInuse(true);
        proxyRepository.save(proxy);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("host", proxy.getHost());
        jsonObject.put("port", proxy.getPort());
        jsonObject.put("ping", proxy.getPingTimeout());

        return jsonObject;
    }
}
