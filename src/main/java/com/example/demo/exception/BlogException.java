package com.example.demo.exception;

import lombok.Data;

@Data
public class BlogException extends RuntimeException {
    /**
     * 异常状态
     * */
    private Integer status;
    /**
     * 异常信息
     * */
    private String msg;

    public BlogException(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BlogException(BlogExceptionEnum blogExceptionEnum) {
        this(blogExceptionEnum.getStatus(),blogExceptionEnum.getMsg());
    }
}
