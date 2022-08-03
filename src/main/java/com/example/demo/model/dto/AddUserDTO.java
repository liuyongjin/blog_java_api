package com.example.demo.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUserDTO {
    @NotNull(message = "用户名不能为空")
    @Size(min = 1, max = 10, message = "username尺寸不对")
    private String username;

    @NotNull(message = "密码不能为空")
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
