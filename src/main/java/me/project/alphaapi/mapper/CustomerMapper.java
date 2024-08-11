package me.project.alphaapi.mapper;

import me.project.alphaapi.dto.CustomerDTO;
import me.project.alphaapi.entity.Customer;

public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }
}
