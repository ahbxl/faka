package com.card.controller;

import com.card.entity.User;
import com.card.entity.constant.SystemConstant;
import com.card.entity.vo.Result;
import com.card.entity.vo.UserVO;
import com.card.service.UserService;
import com.card.util.JwtUtil;
import com.card.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     * 需要管理员权限
     *
     * @param userVO
     * @return
     */
    @PostMapping("/token/selectPage")
    public Result<Object> selectPage(@RequestBody UserVO userVO) {
        return Result.success(userService.selectPage(userVO));
    }

    /**
     * 通过id修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/token/updateById")
    public Result<Object> updateById(@RequestBody User user) {
        User userById = userService.selectById(user.getId());
        if (userById == null) {
            return Result.fail("不存在该用户");
        }
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 通过id删除用户
     * 需要管理员权限
     *
     * @param userVO
     * @return
     */
    @PostMapping("/token/deleteBatchIds")
    public Result<Object> deleteBatchIds(@RequestBody UserVO userVO) {
        userService.deleteBatchIds(userVO.getIds());
        return Result.success();
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return
     */
    @PostMapping("/insert")
    public Result<Object> insert(@Validated @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return Result.fail(0, "参数错误", allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        // 通过shiro默认的加密工具类为注册用户的密码进行加密
        Object salt = ByteSource.Util.bytes(SystemConstant.slat);
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1);
        user.setPassword(simpleHash.toString());
        userService.insert(user);
        return Result.success();
    }

    /**
     * 通过id查询用户
     *
     * @param userVO
     * @return
     */
    @PostMapping("/token/selectById")
    public Result<Object> selectById(@RequestBody UserVO userVO) {
        return Result.success(userService.selectById(userVO.getId()));
    }

    /**
     * 登录
     *
     * @param user 登录的用户对象
     * @return
     */
    @PostMapping("/login")
    public Result<Object> findByUsernameAndPassword(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        Object salt = ByteSource.Util.bytes(SystemConstant.slat);
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), simpleHash.toString(), true);
        try {
            subject.login(usernamePasswordToken);
            // 生成token，token有效时间为30分钟
            String token = JwtUtil.createJWT(String.valueOf(new Date()), user.getUsername(), 3600000L);
            // 将用户户名和token返回
            HashMap<String, String> map = new HashMap<>();
            map.put("username", user.getUsername());
            map.put("token", token);
            return Result.success(map);
        } catch (UnknownAccountException e) {
            return Result.fail("登陆失败！用户名或密码不正确");
        }
    }

    /**
     * 注册时校验用户名是否存在
     *
     * @param username 用户名
     * @return
     */
    @PostMapping("/countUsername/{username}")
    public Result<Object> countUsername(@PathVariable String username) {
        return Result.success(userService.countByUsername(username));
    }

    /**
     * 注销登录
     * 前提是在登录状态
     *
     * @return
     */
    @PostMapping("/loginOut")
    public Result<Object> loginOut() {
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return Result.fail("您暂未登录");
        }
        SecurityUtils.getSubject().logout();
        return Result.success("注销成功");
    }
}