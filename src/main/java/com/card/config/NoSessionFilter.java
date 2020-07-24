package com.card.config;

import com.card.util.TokenSubjectUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class NoSessionFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = request.getParameter("token");
        Subject subject = TokenSubjectUtil.getSubject(token);
        return subject != null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        response.getWriter().print("403");
        return false;
    }
}
