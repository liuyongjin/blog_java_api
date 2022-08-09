package com.example.demo.common;

import com.example.demo.model.vo.ResPageInfoVO;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ApiResponse<T> {
    private Integer status;

    private Boolean success = true;
    private String msg;
    private T data;
    private static final int OK_STATUS = 200;
    private static final String OK_MSG = "SUCCESS";

    public ApiResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(OK_STATUS, OK_MSG);
    }


    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<>(OK_STATUS, OK_MSG, result);
        return response;
    }

    public static ApiResponse<ResPageInfoVO> success(PageInfo result) {
        ResPageInfoVO resPageInfoVO = new ResPageInfoVO<>();
        BeanUtils.copyProperties(result, resPageInfoVO);
        resPageInfoVO.setPageIndex(result.getPageNum());
        ApiResponse<ResPageInfoVO> response = new ApiResponse<>(OK_STATUS, OK_MSG, resPageInfoVO);
        return response;
    }

    public static <T> ApiResponse<T> success(Integer status, String msg, T data) {
        ApiResponse<T> response = new ApiResponse<>(status, msg, data);
        return response;
    }

    public static <T> ApiResponse<T> success(Integer status, String msg) {
        ApiResponse<T> response = new ApiResponse<>(status, msg);
        return response;
    }

    public static <T> ApiResponse<T> error(Integer status, String msg) {
        ApiResponse<T> response = new ApiResponse<>(status, msg);
        response.success = false;
        return response;
    }

    public static <T> ApiResponse<T> error(Integer status, String msg, T data) {
        ApiResponse<T> response = new ApiResponse<>(status, msg, data);
        response.success = false;
        return response;
    }

    public static <T> ApiResponse<T> error(T data) {
        ApiResponse<T> response = new ApiResponse<>(data);
        response.success = false;
        return response;
    }
}
