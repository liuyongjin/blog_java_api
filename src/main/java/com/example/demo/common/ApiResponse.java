package com.example.demo.common;

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
        this.success = true;
    }

    public ApiResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.success = true;
    }

    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(OK_STATUS, OK_MSG);
    }


    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<>(OK_STATUS, OK_MSG, result);
        return response;
    }

    public static <T> ApiResponse<T> success(Integer status, String msg, T data) {
        ApiResponse<T> response = new ApiResponse<>(status, msg, data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
