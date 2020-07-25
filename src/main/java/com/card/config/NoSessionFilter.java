package com.card.config;

import com.card.entity.SystemConstant;
import com.card.entity.vo.CheckResult;
import com.card.util.JwtUtil;
import lombok.SneakyThrows;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class NoSessionFilter extends BasicHttpAuthenticationFilter {
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        // 1.从Cookie获取jwt
        String token = getTokenFromCookie(servletRequest);
        if (StringUtils.isEmpty(token)) {
            // 2.从headers中获取
            token = servletRequest.getHeader("token");
        }
        if (StringUtils.isEmpty(token)) {
            // 3.从请求参数获取
            token = request.getParameter("token");
        }
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        CheckResult checkResult = JwtUtil.validateJWT(token);
        if (checkResult.isSuccess()) {
            request.setAttribute("claims", checkResult.getClaims());
            return true;
        } else {
            if (checkResult.getErrCode().equals(SystemConstant.JWT_ERRCODE_EXPIRE)) {
                response.setCharacterEncoding("utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.write("token过期，请重新登录");
                printWriter.flush();
                printWriter.close();
            } else if (checkResult.getErrCode().equals(SystemConstant.JWT_ERRCODE_FAIL)) {
                PrintWriter printWriter = response.getWriter();
                response.setCharacterEncoding("utf-8");
                printWriter.write("token验证失败！");
                printWriter.flush();
                printWriter.close();
            }
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        response.getWriter().print("403");
        return false;
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        int len = null == cookies ? 0 : cookies.length;
        if (len > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
