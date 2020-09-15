package com.card.controller;

import com.card.entity.domain.User;
import com.card.entity.vo.ResultVO;
import com.card.service.UserService;
import com.card.util.JwtUtil;
import com.card.util.ResultVOUtil;
import com.card.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
public class DefaultController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user 登录的用户对象
     * @return
     */
    @PostMapping("/login")
    public ResultVO<Object> findByUsernameAndPassword(@RequestBody User user) {
        user.validate();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword(),true);
        try {
            subject.login(usernamePasswordToken);
            // 生成token，token有效时间为30分钟
            String token = JwtUtil.createJWT(String.valueOf(new Date()), user.getUsername(), 3600000L);
            // 将用户户名和token返回
            HashMap<String, String> map = new HashMap<>();
            map.put("username", user.getUsername());
            map.put("token", token);
            return ResultVOUtil.success(map);
        } catch (UnknownAccountException e) {
            return ResultVOUtil.fail("登陆失败！用户名或密码不正确");
        }
    }

    /**
     * 注册时校验用户名是否存在
     *
     * @param username 用户名
     * @return
     */
    @PostMapping("/countUsername/{username}")
    public ResultVO<Object> countUsername(@PathVariable String username) {
        return ResultVOUtil.success(userService.countByUsername(username));
    }

    /**
     * 注销登录
     * 前提是在登录状态
     *
     * @return
     */
    @PostMapping("/loginOut")
    public ResultVO<Object> loginOut() {
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return ResultVOUtil.fail("您暂未登录");
        }
        SecurityUtils.getSubject().logout();
        return ResultVOUtil.success("注销成功");
    }
}