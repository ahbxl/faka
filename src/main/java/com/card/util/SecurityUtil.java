package com.card.util;

import com.card.entity.AuthorizedUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SecurityUtil {
    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static AuthorizedUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated() && !subject.isRemembered())
            throw new RuntimeException("Log current user error: UnAuthenticated subject");
        return (AuthorizedUser) subject.getPrincipal();
    }
}