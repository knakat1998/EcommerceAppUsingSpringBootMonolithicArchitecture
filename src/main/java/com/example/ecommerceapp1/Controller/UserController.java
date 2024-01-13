package com.example.ecommerceapp1.Controller;

import com.example.ecommerceapp1.Entity.User;
import com.example.ecommerceapp1.Repository.UserRepository;
import com.example.ecommerceapp1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    private  UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping(value = "/registration")
    public String Signup(@RequestBody User user){
        return userService.signup(user);
    }
    @PostMapping(value = "/login")
    public String login(@RequestBody Map<String,Object> map){
        return  userService.login(map.get("username").toString(), map.get("password").toString());
    }

}
