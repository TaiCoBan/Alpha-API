package me.project.alphaapi.service;

import me.project.alphaapi.entity.OrderItem;

public interface OrderItemService {
    OrderItem save(Long cusId, Long cusOrderId, OrderItem orderItem);
    OrderItem delete(Long cusId, Long cusOrderId, Long orderItemId);
}
