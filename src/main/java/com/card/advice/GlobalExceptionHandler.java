package com.card.advice;

import com.card.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // 其它异常异常捕捉
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> exception(HttpServletRequest req, Exception e) {
        log.error("---BaseException Handler---Host {} invokes url {} ERROR: ", req.getRemoteHost(), req.getRequestURL(), e);
        return Result.fail("系统错误，请联系网站管理员！");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result<Object> runtimeException(HttpServletRequest req, RuntimeException e) {
        log.error("---BaseException Handler---Host {} invokes url {} ERROR: ", req.getRemoteHost(), req.getRequestURL(), e);
        return Result.fail(e.getMessage());
    }

    /**
     * 处理Shiro权限拦截异常
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result<Object> unauthorizedException(HttpServletRequest req, UnauthorizedException e) {
        log.error("---BaseException Handler---Host {} invokes url {} ERROR: ", req.getRemoteHost(), req.getRequestURL(), e);
        return Result.fail("权限不足");
    }
}