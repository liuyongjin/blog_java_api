package com.example.demo.model.dao;

import com.example.demo.model.pojo.LoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogMapper {
    int insertLoginLog(LoginLog loginLog);
}
