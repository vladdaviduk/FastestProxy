package com.core;

import com.core.sheduled.ScheduledProxyQuality;
import com.collection.Proxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.ProxyRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

@Service
public class GetProxy {

    @Autowired
    ProxyRepository proxyRepository;

    @Autowired
    ScheduledProxyQuality scheduledProxyQuality;

    ClassLoader classLoader = getClass().getClassLoader();
    File jsonFile = new File(classLoader.getResource("fastest.json").getFile());

    public JSONObject getFastestProxy() throws IOException {

        LinkedList<Proxy> proxyRating = scheduledProxyQuality.getProxyRating();

        proxyRating.sort(Comparator.comparing(Proxy::getPingTimeout));

        Proxy proxy = proxyRating.get(0);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("host", proxy.getHost());
        jsonObject.put("port", proxy.getPort());
        jsonObject.put("ping", proxy.getPingTimeout());

        proxy.setProxyInuse(true);
        proxyRepository.save(proxy);

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(jsonFile, jsonObject);
//        mapper.writer();

        proxyRating.remove(0);
        scheduledProxyQuality.setProxyRating(proxyRating);

        return jsonObject;
    }
}
