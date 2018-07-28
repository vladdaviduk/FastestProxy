package com.service;

import com.service.collection.Proxy;
import com.google.gson.JsonArray
import com.google.gson.JsonParser;
import com.service.repository.ProxyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service

@Service
class ParseProxy {

    @Autowired
    ProxyRepository proxyRepository;

    void save(){

        ArrayList<Proxy> proxies = new ArrayList<>();
        JsonArray elements = new JsonParser().parse(new URL("http://scraper.elasticdata.io/api/pipeline/data/5b5a1a82e4b06c11508d337b")
                .getText(connectTimeout: 5000, readTimeout: 10000)).getAsJsonArray();

        elements.each { element ->
            Proxy proxy = new Proxy();
            proxy.setHost(element.getAsJsonObject().get("ip").getAsString());
            proxy.setPort(element.getAsJsonObject().get("port").getAsInt());

            if(proxyRepository.findByHostAndPort(proxy.getHost(), proxy.getPort()) == null){
               proxies.add(proxy);
            }
        }
        proxyRepository.save(proxies);
    }
}
