package com.example.demo.common;

import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static final String USER_ID = "id";
    public static final String USER_NAME = "username";
    public static final String NICK_NAME = "nickname";
    public static final String ARTICLE_LIST = "article_list";

    public static final Integer REDIS_CACHE_MINUTES = 300;

    public static final Integer REDIS_TOKEN_VALID_MINUTES = 300;
    public static final Integer REDIS_CACHE_SECONDS = 20;

    public static final String FILE_PATH = "C:\\Users\\Administrator\\Desktop\\demo\\src\\upload";

    public enum CommonEnum {
        CREATE_SUCCESS(200, "删除成功");
        private Integer status;
        /**
         * 异常信息
         */
        private String msg;

        CommonEnum(Integer status, String msg) {
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

}
