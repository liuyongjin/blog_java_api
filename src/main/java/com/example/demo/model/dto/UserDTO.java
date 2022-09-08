package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    public interface Login {

    }

    public interface Insert {
    }

    public interface Update {
    }

    @Null(message = "id必须为空", groups = UserDTO.Insert.class)
    private Integer id;

    @NotNull(message = "用户名不能为空", groups = {UserDTO.Login.class, UserDTO.Insert.class, UserDTO.Update.class})
    @Size(min = 1, max = 10, message = "username尺寸不对")
    private String username;

    private String nickname;

    @NotNull(message = "密码不能为空", groups = {UserDTO.Login.class, UserDTO.Insert.class, UserDTO.Update.class})
    private String password;

    private String token;

    private String avatar;

    private String last_login_ip;

    private String last_login_time;

}
