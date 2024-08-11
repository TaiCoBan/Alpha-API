package me.project.alphaapi.service;

import me.project.alphaapi.dto.CustomerDTO;
import me.project.alphaapi.entity.Customer;

public interface CustomerService {
    Customer save(Customer customer);
    Customer getCurrentCustomer();
    Customer updateCustomer(Long customerId, Customer customer);
}
