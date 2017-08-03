package com.timposu.pembayaran.pembayaranserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by ucup on 02/08/17.
 */

public class LoginRequest {
    @NotNull @NotEmpty
    private String username;

    @NotNull @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
