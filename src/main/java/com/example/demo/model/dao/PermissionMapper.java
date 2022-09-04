package com.example.demo.model.dao;

import com.example.demo.model.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    List<Permission> findPermissionByUserName(int id);
}
