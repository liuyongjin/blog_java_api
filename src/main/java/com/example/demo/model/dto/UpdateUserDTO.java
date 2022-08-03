package com.example.demo.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDTO {

    @NotNull(message = "id不能为空")
    private Integer id;

    @Size(min = 1, max = 10, message = "username尺寸不对")
    private String username;

    //    @Length(min = 1,max = 20)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "UpdateUserReq{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
