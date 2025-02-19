package com.hau.identity_service.dto.request;

import com.hau.identity_service.validator.ValidEmailDomain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserCreationRequest {
    @ValidEmailDomain(message = "INVALID_EMAIL_DOMAIN")
    String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String phoneNumber;
    @NotBlank
    String address;
    int roleId;
}
