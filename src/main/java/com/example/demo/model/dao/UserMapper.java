package com.example.demo.model.dao;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectByName(String username);
    User selectById(int id);

    int updateUser(User user);

    int insertSelective(UserDTO user);

    int deleteByPrimaryKey(Integer id);
}
