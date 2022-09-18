package com.example.demo.service;

import com.example.demo.common.Constant;
import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.model.dao.LoginLogMapper;
import com.example.demo.model.dao.PermissionMapper;
import com.example.demo.model.dao.UserMapper;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.*;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.RedisUtils;
import com.example.demo.util.Toolkit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LoginLogMapper loginLogMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RedisUtils redisUtils;

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

    //    public User selectByName(String username) {
//        return userMapper.selectByName(username);
//    }
    public int updateUser(UserDTO userDTO, Integer userId) {
        User user = new User();
        String username = userDTO.getUsername();
        User findUser = userMapper.selectByName(username);
        if (findUser != null) {
            throw new BlogException(BlogExceptionEnum.USER_IS_EXIST);
        }
        String nickname = userDTO.getNickname();
        String password = userDTO.getPassword();
        String avatar = userDTO.getAvatar();
        user.setId(userId);
        user.setUsername(username);
        user.setNickname(nickname);
        if (password != null) {
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        }
        user.setAvatar(avatar);
        return userMapper.updateUser(user);
    }

    public String login(UserDTO userDTO, HttpServletRequest httpServletRequest) {
        User user = userMapper.selectByName(userDTO.getUsername());
        String password = DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes());
        if (user == null) {
            throw new BlogException(BlogExceptionEnum.USER_NOT_EXIST);
        } else if (!Objects.equals(user.getPassword(), password)) {
            throw new BlogException(BlogExceptionEnum.PASSWORD_ERROR);
        }
        // 生成token
        String ip = Toolkit.getIpAddr(httpServletRequest);
        String userId = user.getId() + "";
        String username = user.getUsername();
        String nickname = user.getNickname();
        String jwtToken = jwtUtil.createToken(userId, username, nickname);
        // TODO: 缓存token到redis中(实现注销, 单设备登录)
        redisUtils.set(userId, jwtToken, Constant.REDIS_CACHE_MINUTES, TimeUnit.MINUTES);
        // 更新token和请求ip信息
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setToken(jwtToken);
        updateUser.setLast_login_ip(ip);
        // 处理成北京时间
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        System.out.println(calendar.getTime().toString());
//        Date date = new Date();
        updateUser.setLast_login_time(date);
        userMapper.updateUser(updateUser);
        // 记录登录信息
        LoginLog loginLog = new LoginLog();
        loginLog.setLogin_name(username);
        loginLog.setLogin_ip(ip);
        loginLogMapper.insertLoginLog(loginLog);
        return jwtToken;
    }

    public int register(UserDTO userDTO) {
        String username = userDTO.getUsername();
        User findUser = userMapper.selectByName(username);
        if (findUser != null) {
            throw new BlogException(BlogExceptionEnum.USER_IS_EXIST);
        }
        UserDTO user = new UserDTO();
        String nickname = userDTO.getNickname();
        String password = DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes());
        String avatar = userDTO.getAvatar();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setAvatar(avatar);
        Integer response = userMapper.insertSelective(user);
        if (response == null) {
            throw new BlogException(BlogExceptionEnum.CREATE_FAILED);
        }
        return response;
    }

    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
