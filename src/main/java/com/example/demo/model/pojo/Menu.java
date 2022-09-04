package com.example.demo.model.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Menu {
    private int id;
    private String menu_name;
    private String menu_desc;
    private String menu_url;
    private int parent_id;
    private List<Menu> children;
    private Date create_time;
    private Date update_time;
    private Date delete_time;
}
