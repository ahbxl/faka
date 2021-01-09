package com.card.security.constant;

public class SystemConstant {
    public static final String JWT_SECERT = "dfb70cce59"; // jwt的盐，不可以泄露
    public static final String JWT_ERRCODE_EXPIRE = "认证已过期";
    public static final String JWT_ERRCODE_FAIL = "认证失败";
    public static final String slat = "ba0ee3fdgd"; // 用户密码加密的盐，不可以泄露
}