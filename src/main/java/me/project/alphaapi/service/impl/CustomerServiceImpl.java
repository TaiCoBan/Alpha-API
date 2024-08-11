package me.project.alphaapi.service.impl;

import lombok.AllArgsConstructor;
import me.project.alphaapi.dto.CustomerDTO;
import me.project.alphaapi.entity.Customer;
import me.project.alphaapi.exception.InvalidParamException;
import me.project.alphaapi.repository.CustomerRepo;
import me.project.alphaapi.service.CustomerService;
import me.project.alphaapi.util.CustomerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerUtil customerUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer save(Customer customer) {
        LOGGER.info("New customer: {}", customer);

        // Check Email
        LOGGER.info("New customer's email: {}", customer.getEmail());
        if (customer.getEmail() == null) {
            throw new InvalidParamException("Email is required");
        }
        if (!customerUtil.checkEmailForm(customer.getEmail())) {
            throw new InvalidParamException("Email is not valid");
        }

        // Check password
//        LOGGER.info("New customer's password: {}", customer.getPassword());
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            throw new InvalidParamException("Password is required");
        }

        LOGGER.info("Save new customer: {}", customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCurrentCustomer() {
        LOGGER.info("Current customer: {}", customerUtil.getCurrentCustomer());
        return customerUtil.getCurrentCustomer();
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        return null;
    }
}
