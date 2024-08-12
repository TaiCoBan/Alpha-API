package me.project.alphaapi.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.project.alphaapi.entity.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phoneNumber;

    public CustomerDTO(String firstName, String lastName, @Email(message = "Please provide a valid email address") String email, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address.toString();
        this.phoneNumber = phoneNumber;
    }
}
