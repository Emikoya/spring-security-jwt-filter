package com.example.demo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/admin")
    public String getHelloAdmin() throws Exception {
        return "hello admin";
    }

    @GetMapping("/super_admin")
    public String getHelloSuperAdmin() throws Exception {
        return "hello super admin";
    }

    @GetMapping("/user")
    public String getHelloUser() throws Exception {
        return "hello user";
    }

    @GetMapping("/public")
    public String getHelloPublic() throws Exception {
        return "hello public";
    }

    @GetMapping("/private")
    public String getHelloPrivate() throws Exception {
        return "hello private";
    }
}
