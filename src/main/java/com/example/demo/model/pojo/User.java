package com.example.demo.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
//    暂时修改为String类型
    private String id;

    private String username;

    private String nickname;

    private String token;

    private String avatar;

    private Date lastLoginTime;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;
}
