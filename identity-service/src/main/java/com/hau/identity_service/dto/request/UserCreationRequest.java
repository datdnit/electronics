package com.hau.identity_service.dto.request;

import com.hau.identity_service.validator.ValidEmailDomain;
import jakarta.validation.constraints.Size;

public class UserCreationRequest {
    @ValidEmailDomain(message = "INVALID_EMAIL_DOMAIN")
    private String email;
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private int roleId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}