package com.example.AddressBookWorkShop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookEntryDTO {
    @NotNull (message = "Name cannot be null")
    private String name;

    @NotNull (message = "Phone number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Email (message = "Email should be valid")
    private String email;

    @NotNull (message = "Address cannot be null")
    private String address;
}
