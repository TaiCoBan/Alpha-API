package me.project.alphaapi.controller;

import me.project.alphaapi.entity.OrderItem;
import me.project.alphaapi.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("customers/{cusId}/customer-orders/{cusOrderId}/")
    public ResponseEntity<?> saveOrderItem(@PathVariable("cusId") Long cusId,
                                           @PathVariable("cusOrderId") Long cusOrderId,
                                           @RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(orderItemService.save(cusId, cusOrderId, orderItem));
    }

    @DeleteMapping("customers/{cusId}/customer-orders/{cusOrderId}/order-items/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable("cusId") Long cusId,
                                             @PathVariable("cusOrderId") Long cusOrderId,
                                             @PathVariable Long orderItemId) {
        return ResponseEntity.ok(orderItemService.delete(cusId, cusOrderId, orderItemId));
    }
}
