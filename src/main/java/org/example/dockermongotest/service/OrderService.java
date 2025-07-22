package org.example.dockermongotest.service;

import org.example.dockermongotest.model.OrderDetails;
import org.example.dockermongotest.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(OrderDetails orderDetails) {
        orderRepository.save(orderDetails);
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteByOrderId(orderId);
    }
}
