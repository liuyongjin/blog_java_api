package com.example.demo.exception;


import com.example.demo.common.ApiResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse handleException(Exception e) {
        return ApiResponse.error(BlogExceptionEnum.SYSTEM_ERROR.getStatus(), e.toString());
    }

    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public ApiResponse handleBlogException(BlogException e) {
        return ApiResponse.error(e.getStatus(), e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleBindingResult(e.getBindingResult());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ApiResponse BindExceptionHandler(BindException e) {
        return handleBindingResult(e.getBindingResult());
    }

    private ApiResponse handleBindingResult(BindingResult result) {
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        return ApiResponse.error(BlogExceptionEnum.REQUEST_PARAM_ERROR.getStatus(), String.join(",", list));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ApiResponse handleConstraintViolationExceptionException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return ApiResponse.error(BlogExceptionEnum.REQUEST_PARAM_ERROR.getStatus(), message);
    }
}
