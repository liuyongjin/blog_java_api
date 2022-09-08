package com.example.demo.interceptor;

import com.example.demo.common.Constant;
import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
//import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.PassToken;
import com.example.demo.util.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
    //    @Autowired
//    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从请求头中取出 token 这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 默认全部检查
        else {
            // 执行认证
            if (token == null) {
                // 登录失效
                throw new BlogException(BlogExceptionEnum.NEED_LOGIN);
            }
            // 获取 token 中的 userId
            String userId = JwtUtil.getAudience(token);
            // TODO: 找找看是否有这个user 因为我们需要检查用户是否存在，可以自行修改逻辑
            // AccountDTO user = accountService.getByUserName(userId);
            if (userId == null) {
                // 这个错误也是我自定义的
                throw new BlogException(BlogExceptionEnum.USER_NOT_EXIST);
            }
            // 判断redis中是否存在token, token是否失效
            RedisUtils redisUtils = RedisUtils.getInstance();
            if (redisUtils.get(userId) == null) {
                throw new BlogException(BlogExceptionEnum.TOKEN_ERROR);
            }
            // 验证 token
            JwtUtil.verifyToken(token, userId);
            // 获取载荷内容
            String username = JwtUtil.getClaimByName(token, Constant.USER_NAME).asString();
            String nickname = JwtUtil.getClaimByName(token, Constant.NICK_NAME).asString();

            // 放入attribute以便后面调用
            httpServletRequest.setAttribute(Constant.USER_ID, userId);
            httpServletRequest.setAttribute(Constant.USER_NAME, username);
            httpServletRequest.setAttribute(Constant.NICK_NAME, nickname);
            return true;
        }
        return true;
    }

    // @Override
    // public void postHandle(HttpServletRequest httpServletRequest,
    // HttpServletResponse httpServletResponse,
    // Object o, ModelAndView modelAndView) throws Exception {
    //
    // }
    //
    // @Override
    // public void afterCompletion(HttpServletRequest httpServletRequest,
    // HttpServletResponse httpServletResponse,
    // Object o, Exception e) throws Exception {
    // }
}
