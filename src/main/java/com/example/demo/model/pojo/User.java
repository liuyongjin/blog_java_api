package com.example.demo.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"password"})
public class User {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String token;

    private String avatar;

    private List<String> authorities;

    private List<Menu> menu_list;

    private List<Role> role_list;

    private String last_login_ip;

    private Date last_login_time;

    private Date create_time;

    private Date update_time;
}
