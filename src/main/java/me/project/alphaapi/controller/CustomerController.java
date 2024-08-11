package me.project.alphaapi.controller;

import me.project.alphaapi.entity.Customer;
import me.project.alphaapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }

    @GetMapping
    public ResponseEntity<?> getCurrentCustomer() {
        return ResponseEntity.ok(customerService.getCurrentCustomer());
    }
}
