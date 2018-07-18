package com.repository;

import com.entity.Proxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyRepository extends MongoRepository<Proxy, String> {


}
