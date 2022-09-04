package com.example.demo.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private int id;
    private String role_name;
    private String role_desc;
    private Date create_time;
    private Date update_time;
    private Date delete_time;
}
