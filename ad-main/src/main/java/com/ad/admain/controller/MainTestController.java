package com.ad.admain.controller;

import com.ad.admain.dto.GenericUser;
import com.ad.admain.service.GenericUserService;
import com.ad.message.config.EasemobProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wezhyn
 * @date : 2019/09/20
 * <p>
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
@RestController
public class MainTestController {
    @Autowired
    private GenericUserService genericUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/api/test/register")
    public String register(String username) {
        genericUserService.save(
                GenericUser.newBuilder().username(username)
                .password(passwordEncoder.encode("111111")).build()
        );
        return "true";
    }

}