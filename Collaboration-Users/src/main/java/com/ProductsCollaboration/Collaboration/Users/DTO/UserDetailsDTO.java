package com.ProductsCollaboration.Collaboration.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    private String firstName;

    private String lastName;

    private String Email;

    private String Address;

    private String phoneNum;

    private String Role;
}
