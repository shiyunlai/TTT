package org.tis.tools.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/7/27
 **/
@RestController
public class GateController {

    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/users")
    public Principal user(Principal user) {
        return user;
    }
}
