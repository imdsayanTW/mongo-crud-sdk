// src/test/java/org/example/dockermongotest/service/OrderServiceIntegrationTest.java
package org.example.dockermongotest.service;

import org.example.dockermongotest.model.OrderDetails;
import org.example.dockermongotest.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "spring.data.mongodb.uri=mongodb://localhost:27017/testdb"
})
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrderDetailsThroughService() {
        OrderDetails order = new OrderDetails("2", "Gadget", 10);
        orderService.saveOrder(order);

        OrderDetails found = orderRepository.findByOrderId("2");
        assertThat(found).isNotNull();
        assertThat(found.getProductName()).isEqualTo("Gadget");
        assertThat(found.getQuantity()).isEqualTo(10);
    }

    @Test
    void testDeleteOrderByOrderId() {
        OrderDetails orderA = new OrderDetails("3", "Cake", 5);
        OrderDetails orderB = new OrderDetails("2", "Balloon", 5);
        orderRepository.save(orderA);
        orderRepository.save(orderB);
        System.out.println("Order details before delete - " + orderRepository.findAllOrders().toString());

        orderService.deleteOrder("3");
        OrderDetails found = orderRepository.findByOrderId("3");
        System.out.println("Order details after delete - " + orderRepository.findAllOrders().toString());
        assertThat(found).isNull();
    }

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
    }
}