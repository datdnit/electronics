package com.hau.identity_service.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)


public class UserResponse {
    int id;
    String email;
    String password;
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
    int roleId;
}
