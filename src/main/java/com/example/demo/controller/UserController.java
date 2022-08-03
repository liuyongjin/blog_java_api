package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.model.dto.AddUserDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dao.UserMapper;
import com.example.demo.model.dto.UpdateUserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/current")
    public ApiResponse currentUser() {
        User response = userService.selectById(1);
        return ApiResponse.success(response);
    }

    @GetMapping("/list")
    public User getUser() {
//        return 123123;
//        return userService.selectByName();
        return userMapper.selectByName();
    }

    //    @PostMapping("/updateUser")
//    @ResponseBody
    @PostMapping("/update")
    public ApiResponse updateUser(@Validated UpdateUserDTO updateUserDTO) {
        System.out.println(updateUserDTO.getUsername());
        int response = userService.updateUser(updateUserDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/register")
    public ApiResponse register(@Validated AddUserDTO addUserDTO) {
        System.out.println(addUserDTO);
        int response = userService.register(addUserDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/delete")
    public ApiResponse delete(Integer id) {
        int response = userService.delete(id);
        return ApiResponse.success(response);
    }
}
