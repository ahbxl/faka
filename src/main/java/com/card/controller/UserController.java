package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.user.UserCommand;
import com.card.entity.domain.User;
import com.card.entity.vo.ResultVO;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param pageNum  当前页
     * @param pageSize 页大小
     * @param command  查询对象
     * @return
     */
    @PostMapping("/admin/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody UserCommand command) {
        return ResultVOUtil.success(userService.selectByPage(pageNum, pageSize, command));
    }

    /**
     * 通过id修改用户
     *
     * @param id   id
     * @param user 用户对象
     * @return
     */
    @PostMapping("/admin/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody User user) {
        user.validate();
        User userById = userService.selectById(id);
        if (userById == null) {
            return ResultVOUtil.fail("不存在该用户");
        }
        userService.updateById(id, user);
        return ResultVOUtil.success();
    }

    /**
     * 通过id删除用户
     * 需要管理员权限
     *
     * @param id id
     * @return
     */
    @PostMapping("/admin/deleteById/{id}")
    public ResultVO<Object> deleteById(@PathVariable("id") Long id) {
        User user = userService.selectById(id);
        if (user == null) {
            return ResultVOUtil.fail("不存在该用户");
        }
        userService.deleteById(id);
        return ResultVOUtil.success();
    }

    /**
     * 通过id批量删除用户
     * 需要管理员权限
     *
     * @param command
     * @return
     */
    @PostMapping("/admin/deleteByIds")
    public ResultVO<Object> deleteByIds(@RequestBody IdsCommand command) {
        command.validate();
        List<Long> ids = Lists.newArrayList();
        for (Long id : command.getIds()) {
            User user = userService.selectById(id);
            if (user == null) {
                log.info("不存在该用户:" + id);
            } else {
                ids.add(user.getId());
            }
        }
        userService.deleteByIds(ids);
        return ResultVOUtil.success();
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return
     */
    @PostMapping("/insert")
    public ResultVO<Object> insert(@RequestBody User user) {
        user.validate();
        userService.insert(user.doBuild());
        return ResultVOUtil.success();
    }

    /**
     * 通过id查询用户
     *
     * @param id id
     * @return
     */
    @PostMapping("/admin/selectById/{id}")
    public ResultVO<Object> selectById(@PathVariable("id") Long id) {
        User user = userService.selectById(id);
        if (user == null) {
            return ResultVOUtil.fail("不存在该用户");
        }
        return ResultVOUtil.success(user);
    }
}