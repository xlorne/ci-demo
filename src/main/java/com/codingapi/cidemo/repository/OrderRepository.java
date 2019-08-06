package com.codingapi.cidemo.repository;

import com.codingapi.cidemo.collection.Logger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lorne
 * @date 2019/8/6
 * @description
 */
@Repository
public interface OrderRepository extends MongoRepository<Logger,String> {


}
