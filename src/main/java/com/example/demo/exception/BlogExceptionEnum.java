package com.example.demo.exception;

public enum BlogExceptionEnum {
    TOKEN_ERROR(10001, "token无效"),
    TOKEN_PARSE_ERROR(10002, "token解析失败"),

    FILE_UPLOAD_ERROR(10003, "文件上传失败"),
    SELECT_FAILED(10005, "查询失败"),
    NEED_LOGIN(10006, "需要登录"),
    UPDATE_FAILED(10008, "更新失败"),
    LOGIN_FAILED(10009, "登录失败"),
    CREATE_FAILED(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    DELETE_FAILED(10013, "删除失败"),
    USER_NOT_EXIST(10014, "用户不存在"),

    USER_IS_EXIST(10015, "用户已存在"),
    PASSWORD_ERROR(10015, "密码错误"),
    SYSTEM_ERROR(20000, "系统异常，请从控制台或日志中查看具体错误信息");
    /**
     * 异常状态
     */
    private Integer status;

    /**
     * 异常信息
     */
    private String msg;

    BlogExceptionEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
