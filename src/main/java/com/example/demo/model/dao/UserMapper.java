package com.example.demo.model.dao;

import com.example.demo.model.dto.AddUserDTO;
import com.example.demo.model.pojo.User;
import com.example.demo.model.dto.UpdateUserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectByName(String username);
    User selectById(int id);

    int updateUser(UpdateUserDTO updateUserDTO);

    int insertSelective(AddUserDTO user);

    int deleteByPrimaryKey(Integer id);
}
