package com.card.controller;

import com.card.entity.User;
import com.card.entity.vo.Result;
import com.card.entity.vo.UserVO;
import com.card.security.utils.SecurityUtil;
import com.card.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/user")
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
    @PostMapping("/selectPage")
    @RequiresPermissions({"user:select"})
    public Result<Object> selectPage(@RequestBody UserVO userVO) {
        return Result.success(userService.selectPage(userVO));
    }

    /**
     * 添加或或者修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @RequiresPermissions({"user:update"})
    public Result<Object> saveOrUpdate(@RequestBody User user) {
        Integer count = userService.lambdaQuery().eq(User::getUsername, user.getUsername())
                .ne(user.getId() != null, User::getId, user.getId())
                .count();
        if (count > 0) return Result.fail("用户名已存在");
        userService.saveOrUpdate(user);
        return Result.success();
    }

    /**
     * 通过id删除用户
     * 需要管理员权限
     *
     * @param userVO
     * @return
     */
    @PostMapping("/removeById")
    @RequiresPermissions({"user:delete"})
    public Result<Object> removeById(@RequestBody UserVO userVO) {
        userService.removeById(userVO.getId());
        return Result.success();
    }

    /**
     * 通过id查询用户
     *
     * @param userVO
     * @return
     */
    @PostMapping("/getById")
    @RequiresPermissions({"user:select"})
    public Result<Object> selectById(@RequestBody UserVO userVO) {
        return Result.success(userService.getById(userVO.getId()));
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