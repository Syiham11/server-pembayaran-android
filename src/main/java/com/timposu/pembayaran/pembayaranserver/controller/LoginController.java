package com.timposu.pembayaran.pembayaranserver.controller;

import com.timposu.pembayaran.pembayaranserver.dto.LoginRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

/**
 * Created by ucup on 02/08/17.
 */

@RestController
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody @Valid LoginRequest login) {
        String validUsername = "ucup";
        String validPassword = "123";

        Map<String, Object> hasil = new HashMap<>();

        if (validUsername.equals(login.getUsername()) &&
                validPassword.equals(login.getPassword())){
            hasil.put("success", true);
        } else {
            hasil.put("success", false);
        }

        return hasil;
    }
}
