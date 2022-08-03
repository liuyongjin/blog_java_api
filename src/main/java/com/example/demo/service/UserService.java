package com.example.demo.service;

import com.example.demo.model.dao.UserMapper;
import com.example.demo.model.dto.AddUserDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dto.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    public User selectByName() {
        return userMapper.selectByName();
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
