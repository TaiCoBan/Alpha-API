package me.project.alphaapi.service.impl;

import me.project.alphaapi.entity.OrderItem;
import me.project.alphaapi.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Override
    public OrderItem save(Long cusId, Long cusOrderId, OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem delete(Long cusId, Long cusOrderId, Long orderItemId) {
        return null;
    }
}
