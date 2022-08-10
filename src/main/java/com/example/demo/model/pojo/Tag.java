package com.example.demo.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Tag {
    private Integer id;

    private String name;

    private String des;

    private String color;

    private String bg_color;

    private Date create_time;

    private Date update_time;

    private Date delete_time;
}
