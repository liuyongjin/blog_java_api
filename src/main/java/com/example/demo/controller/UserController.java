package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dto.AddUserDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dao.UserMapper;
import com.example.demo.model.dto.UpdateUserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

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
    @PostMapping("/update")
    public ApiResponse updateUser(@Validated UpdateUserDTO updateUserDTO) {
        System.out.println(updateUserDTO.getUsername());
        int response = userService.updateUser(updateUserDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/login")
    public ApiResponse login(@NotEmpty(message = "用户名不能为空") String username, @NotEmpty(message = "密码不能为空") String password) {
        try {
            // TODO: 判断登录逻辑
//            userService.login

            // 如果成功了，聚合需要返回的信息
            User user = userService.selectByName(username);
//            System.out.println(user);
            //给分配一个token 然后返回
            String jwtToken = JwtUtil.createToken(user.getId(), user.getUsername(), user.getNickname());
            //我的处理方式是把token放到accountVO里去了
            user.setToken(jwtToken);
            return ApiResponse.success(user);
        } catch (Exception e) {
            throw new BlogException(BlogExceptionEnum.LOGIN_FAILED);
        }
    }

    @PostMapping("/register")
    public ApiResponse register(@Validated AddUserDTO addUserDTO) {
        int response = userService.register(addUserDTO);
        return ApiResponse.success(response);
    }

    @PostMapping("/delete")
    public ApiResponse delete(Integer id) {
        int response = userService.delete(id);
        return ApiResponse.success(response);
    }
}
