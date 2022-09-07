package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
//import com.example.demo.exception.BlogException;
//import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dao.UserMapper;
//import com.example.demo.model.dto.UpdateUserDTO;
import com.example.demo.service.UserService;
//import com.example.demo.util.JwtUtil;
import com.example.demo.util.PassToken;
import com.example.demo.util.Toolkit;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PassToken
    @PostMapping("/current")
    public ApiResponse currentUser() {
        User response = userService.selectById(1);
        return ApiResponse.success(response);
    }

//    @GetMapping("/list")
//    public User getUser() {
//        return 123123;
//        return userService.selectByName();
//        return userMapper.selectByName();
//    }

    //    @PostMapping("/updateUser")
//    @ResponseBody
//    @PostMapping("/update")
//    public ApiResponse updateUser(@Validated UpdateUserDTO updateUserDTO) {
//        System.out.println(updateUserDTO.getUsername());
//        int response = userService.updateUser(updateUserDTO);
//        return ApiResponse.success(response);
//    }

    @PostMapping("/login")
    public ApiResponse login(@Validated(UserDTO.Login.class) UserDTO userDTO, HttpServletRequest request) {
        String ip = Toolkit.getIpAddr(request);
        String token = userService.login(userDTO, ip);
        // 返回token
        return ApiResponse.success(token);
    }

    @PostMapping("/register")
    public ApiResponse register(@Validated UserDTO userDTO) {
        int response = userService.register(userDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/delete")
    public ApiResponse delete(Integer id) {
        int response = userService.delete(id);
        return ApiResponse.success(response);
    }
}
