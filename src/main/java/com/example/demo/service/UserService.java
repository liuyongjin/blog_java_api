package com.example.demo.service;

import com.example.demo.model.dao.PermissionMapper;
import com.example.demo.model.dao.UserMapper;
import com.example.demo.model.dto.AddUserDTO;
import com.example.demo.model.pojo.Menu;
import com.example.demo.model.pojo.Permission;
import com.example.demo.model.pojo.Role;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dto.UpdateUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionMapper permissionMapper;

    public User selectById(int id) {
        List<Permission> permissionList = permissionMapper.findPermissionByUserName(id);
        User user = userMapper.selectById(id);
        List<Menu> menu_list = new ArrayList<>();
        List<Role> role_list = new ArrayList<>();
        for (Permission permission : permissionList) {
            permission.getPermission_value();
            List<String> menu_url_arr = Arrays.asList(permission.getMenu_url().split("/"));
            StringBuffer base_menu_url = new StringBuffer();
            for (String menu_url : menu_url_arr) {
                if (menu_url != null) {
                    base_menu_url.append(menu_url);
                }
            }
            List<String> permission_value_arr = Arrays.asList(permission.getPermission_value().split(","));
            List<String> authorities = new ArrayList<>();
            for (String permission_value : permission_value_arr) {
                authorities.add(base_menu_url + "." + permission_value);
            }
            Menu menu = new Menu();
            Role role = new Role();
            BeanUtils.copyProperties(permission, menu);
            BeanUtils.copyProperties(permission, role);
            menu.setId(permission.getMenu_id());
            role.setId(permission.getRole_id());
            menu_list.add(menu);
            role_list.add(role);
            user.setAuthorities(authorities);
        }
        user.setMenu_list(menu_list);
        user.setRole_list(role_list);
        return user;
    }

    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    public int updateUser(UpdateUserDTO updateUserDTO) {
        return userMapper.updateUser(updateUserDTO);
    }


    public int register(AddUserDTO addUserDTO) {
        AddUserDTO user = new AddUserDTO();
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());
        return userMapper.insertSelective(user);
    }

    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
