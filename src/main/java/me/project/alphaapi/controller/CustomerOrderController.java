package me.project.alphaapi.controller;

import jakarta.servlet.ServletResponse;
import me.project.alphaapi.entity.CustomerOrder;
import me.project.alphaapi.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("customers/{cusId}/")
    public ResponseEntity<?> saveOrder(@PathVariable int cusId,
                                       @RequestBody CustomerOrder customerOrder) {
        return ResponseEntity.ok(customerOrderService.save(customerOrder));
    }
}
