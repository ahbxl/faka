package com.card.controller;

import com.card.entity.User;
import com.card.entity.constant.SystemConstant;
import com.card.entity.vo.Result;
import com.card.entity.vo.UserVO;
import com.card.service.UserService;
import com.card.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class DefaultController {
    @Autowired
    private UserService userService;

    /**
     * 注册用户
     *
     * @param userVO
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public Result<Object> insert(@Validated @RequestBody UserVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return Result.fail(0, "参数错误", allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        // 通过shiro默认的加密工具类为注册用户的密码进行加密
        Object salt = ByteSource.Util.bytes(SystemConstant.slat);
        SimpleHash simpleHash = new SimpleHash("MD5", userVO.getPassword(), salt, 1);
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setPassword(simpleHash.toString());
        user.setEmail(userVO.getEmail());
        if (StringUtils.isNotBlank(userVO.getQq())) {
            user.setQq(userVO.getQq());
        }
        if (StringUtils.isNotBlank(userVO.getPhone())) {
            user.setPhone(userVO.getPhone());
        }
        userService.saveOrUpdate(user);
        return Result.success();
    }

    /**
     * 登录
     *
     * @param user 登录的用户对象
     * @return
     */
    @PostMapping("/login")
    public Result<Object> findByUsernameAndPassword(@RequestBody User user) {
        Object salt = ByteSource.Util.bytes(SystemConstant.slat);
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), simpleHash.toString(), true);
        try {
            // shiro验证用户名密码
            SecurityUtils.getSubject().login(usernamePasswordToken);
            // 生成token，token有效时间为30分钟
            String token = JwtUtil.createJWT(String.valueOf(new Date()), user.getUsername(), 3600000L);
            // 将用户户名和token返回
            HashMap<String, String> map = new HashMap<>();
            map.put("username", user.getUsername());
            map.put("Authorization", token);
            return Result.success(map);
        } catch (Exception e) {
            return Result.fail("登陆失败！用户名或密码不正确");
        }
    }

    /**
     * 注册时校验用户名是否存在
     *
     * @param username 用户名
     * @return
     */
    @PostMapping("/countUsername")
    public Result<Object> countUsername(@PathVariable String username) {
        return Result.success(userService.countByUsername(username));
    }

    /**
     * 403
     *
     * @return
     */
    @PostMapping("/403")
    public String unauthorized() {
        return "/error/403.html";
    }
}
