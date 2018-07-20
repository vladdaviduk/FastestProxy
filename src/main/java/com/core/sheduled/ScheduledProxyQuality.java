package com.core.sheduled;

import com.core.groovy.CheckProxy;
import com.collection.Proxy;

import com.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScheduledProxyQuality {

    @Autowired
    ProxyRepository proxyRepository;

    LinkedList<Proxy> proxyRating = new LinkedList<>();

    public  LinkedList<Proxy> getProxyRating() {
        return proxyRating;
    }

    public void setProxyRating(LinkedList<Proxy> proxyRating) {
        this.proxyRating = proxyRating;
    }

    @Scheduled(fixedRate = 900000)
    public void checkAllProxies() {

        CheckProxy checkProxy = new CheckProxy();

        int count = (int)proxyRepository.count(), page = -1, pageSize = 10;

        while(page * 10 < count) {

            ++page;
            if(count - (page * 10) < 10) {
                pageSize = count - (page * 10);
                page = (count / pageSize);
            }

            Pageable pageable = new PageRequest(page, pageSize);

            Page<Proxy> pages = proxyRepository.findByProxyInuseIsFalse(pageable);

            for (Proxy proxy : pages) {

                long ping = checkProxy.ping(proxy.getHost(), proxy.getPort());

                if (ping != 0 && ping < 3000) {
                    proxy.setPingTimeout((int)ping);
                    proxy.setPingSuccess(true);
                    proxyRepository.save(proxy);

                    for(int i = 0; i < proxyRating.size(); i++){
                       if(proxyRating.get(i).getId().equals(proxy.getId())){
                           proxyRating.remove(i);
                           break;
                       }
                    }
                    proxyRating.add(proxy);

                } else {
                    proxy.setPingTimeout(0);
                    proxy.setPingSuccess(false);
                    proxyRepository.save(proxy);
                }
            }
        }
    }
}
