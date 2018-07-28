package com.service.sheduled;

import com.service.CheckProxy;
import com.service.collection.Proxy;

import com.service.repository.ProxyRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class ScheduledProxyQuality {

    @Autowired
    ProxyRepository proxyRepository;


    @Scheduled(fixedRateString  =  "300000")
    void checkAllProxies() {

        CheckProxy checkProxy = new CheckProxy();
        long proxiesCount = proxyRepository.count();
        int pageSize= 20;

        for (int page = 0; page < proxiesCount / pageSize; page++) {
            updatePing(checkProxy, page, pageSize);
        }
    }

    private void updatePing(CheckProxy checkProxy, int page, int pageSize) {

        Pageable pageable = new PageRequest(page, pageSize);
        Page<Proxy> proxies = proxyRepository.findByProxyInuseIsFalse(pageable);
        proxies.each { proxy ->
            long ping = checkProxy.ping(proxy.getHost(), proxy.getPort());
            if (ping > 0 && ping < 3000) {
                proxy.setPingTimeout((int) ping);
                proxy.setPingSuccess(true);
            } else {
                proxy.setPingTimeout(0);
                proxy.setPingSuccess(false);
            }
            proxyRepository.save(proxy);
        }
    }
}
