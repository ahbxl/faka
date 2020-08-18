package com.card.util;

import com.card.entity.domain.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SecurityUtil {
    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static Admin getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated() && !subject.isRemembered())
            throw new RuntimeException("Log current user error: UnAuthenticated subject");
        return (Admin) subject.getPrincipal();
    }
}