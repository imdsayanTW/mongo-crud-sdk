package org.example.dockermongotest.repository;

import org.example.dockermongotest.model.OrderDetails;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    private final MongoTemplate mongoTemplate;

    public OrderRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderDetails orderDetails) {
        mongoTemplate.save(orderDetails);
    }

    public OrderDetails findByOrderId(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(orderId));
        return mongoTemplate.findOne(query, OrderDetails.class);

    }

    public void deleteByOrderId(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(orderId));
        mongoTemplate.remove(query, OrderDetails.class);
    }

    public List<OrderDetails> findAllOrders() {
        return mongoTemplate.findAll(OrderDetails.class);
    }

    public void deleteAll() {
        mongoTemplate.dropCollection(OrderDetails.class);
    }
}
