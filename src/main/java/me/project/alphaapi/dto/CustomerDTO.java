package me.project.alphaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phoneNumber;
}
