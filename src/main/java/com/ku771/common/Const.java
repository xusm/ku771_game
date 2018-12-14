package com.ku771.common;

/**
 * 全局常量
 * Created by eric on 2018/12/06 0012.
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    public static final String ZIP_IMAGE_URI = "E://ZIPFILE//";

    // 角色类型
    public interface Role{
        int ROLE_ADMIN = 1;  // 管理员
        int ROLE_CUSTOMER = 2; // 普通用户
    }

    // 角色类型
    public enum RoleEnum{
        ADMIN(0,"管理员"),
        CUSTOMER(1,"普通用户");

        private int code;
        private String desc;

        RoleEnum(int code,String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
