package com.example.demo.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Permission {
    private int id;
    private String permission_name;
    private String permission_value;
    private int menu_id;
    private String menu_name;
    private String menu_desc;
    private String menu_url;
    private int role_id;
    private String role_name;
    private String role_desc;
    private int parent_id;
    private Date create_time;
    private Date update_time;
    private Date delete_time;
}
