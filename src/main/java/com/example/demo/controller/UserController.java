package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
//import com.example.demo.exception.BlogException;
//import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.common.Constant;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dao.UserMapper;
//import com.example.demo.model.dto.UpdateUserDTO;
import com.example.demo.service.UserService;
//import com.example.demo.util.JwtUtil;
import com.example.demo.util.PassToken;
import com.example.demo.util.RedisUtils;
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

    @Autowired
    RedisUtils redisUtils;

    @PostMapping("/currentUser")
    public ApiResponse currentUser(HttpServletRequest request) {
        String id = (String) request.getAttribute(Constant.USER_ID);
        Integer userId = Integer.valueOf(id);
        System.out.println(userId);
        User response = userService.selectById(userId);
        return ApiResponse.success(response);
    }

//    @GetMapping("/list")
//    public User getUser() {
//        return 123123;
//        return userService.selectByName();
//        return userMapper.selectByName();
//    }

    @ResponseBody
    @PostMapping("/update")
    public ApiResponse updateUser(@Validated(UserDTO.Insert.class) UserDTO userDTO) {
        int response = userService.updateUser(userDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/login")
    public ApiResponse login(@Validated(UserDTO.Login.class) UserDTO userDTO, HttpServletRequest httpServletRequest) {
        String token = userService.login(userDTO, httpServletRequest);
        // 返回token
        return ApiResponse.success(token);
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {
        String id = (String) request.getAttribute(Constant.USER_ID);
        redisUtils.delete(id);
        // 返回token
        return ApiResponse.success();
    }

    @PostMapping("/register")
    public ApiResponse register(@Validated(UserDTO.Insert.class) UserDTO userDTO) {
        int response = userService.register(userDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/delete")
    public ApiResponse delete(Integer id) {
        int response = userService.delete(id);
        return ApiResponse.success(response);
    }
}
