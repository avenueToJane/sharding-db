package com.example.demo.controller;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.example.demo.config.IdCreator;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shidong.wu on 2018/1/27.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public IdGenerator idGenerator;


    @GetMapping("/add")
    public Object add() {
       /* for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId((long) i);
           order.setOrderId((long) i);
           orderRepository.save(order);
        }
        for (int i = 10; i < 20; i++) {
            Order order = new Order();
            order.setUserId((long) i + 1);
            order.setOrderId((long) i);
            orderRepository.save(order);
       }*/

/**
 *

 Sharding-JDBC采用snowflake算法作为默认的分布式分布式自增主键策略，用于保证分布式的情况下可以无中心化的生成不重复的自增序列。

 因此自增主键可以保证递增，但无法保证连续。而snowflake算法的最后4位是在同一毫秒内的访问递增值。因此，如果毫秒内并发度不高，最后4位为零的几率则很大。因此并发度不高的应用生成偶数主键的几率会更高。

 */
        for (long i = 1; i < 100; i++) {
            Order  order = new Order();
            order.setUserId(i);
           // order.setOrderId(idGenerator.generateId().longValue());
            order.setOrderId( Long.parseLong(IdCreator.getId(20)));
            //order.setOrderId(303560365776568401L);
            orderRepository.save(order);
           System.out.println(idGenerator.generateId().longValue());
        }
        return "success";
    }



    @RequestMapping("query")
    private Object queryAll() {
        return orderRepository.findAll();
    }
}
