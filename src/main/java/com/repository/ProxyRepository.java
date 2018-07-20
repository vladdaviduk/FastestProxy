package com.repository;

import com.collection.Proxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyRepository extends MongoRepository<Proxy, String> {

    Page<Proxy> findByProxyInuseIsFalse(Pageable pageable);


}
