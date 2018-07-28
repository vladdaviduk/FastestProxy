package com.service.repository;

import com.service.collection.Proxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProxyRepository extends MongoRepository<Proxy, String> {

    Page<Proxy> findByProxyInuseIsFalse(Pageable pageable);

    Proxy findFirstByProxyInuseIsFalseAndPingSuccessIsTrueOrderByPingTimeoutAsc();

    Proxy findByHostAndPort(String host, int port);

}
