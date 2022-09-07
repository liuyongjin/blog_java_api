package com.example.demo.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLog {
    private int id;
    private String login_name;
    private String login_ip;
    private Date login_time;
    private Date create_time;
    private Date update_time;
    private Date delete_time;
}
