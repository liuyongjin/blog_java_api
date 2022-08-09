package com.example.demo.common;

public class Constant {
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
