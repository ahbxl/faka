package com.card.controller;

import com.card.command.IdsCommand;
import com.card.command.product.ProductFindCommand;
import com.card.command.user.UserCommand;
import com.card.entity.domain.Product;
import com.card.entity.domain.User;
import com.card.entity.vo.ResultVO;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/user/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody UserCommand command) {
        return ResultVOUtil.success(userService.findByPage(pageNum, pageSize, command));
    }

    /**
     * 通过id修改用户
     * 需要管理员权限
     *
     * @param id   id
     * @param user 用户对象
     * @return
     */
    @PostMapping("/user/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody User user) {
        user.validate();
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
    @PostMapping("/user/deleteById/{id}")
    public ResultVO<Object> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResultVOUtil.success();
    }

    /**
     * 添加用户
     * 需要管理员权限
     *
     * @param user 用户对象
     * @return
     */
    @PostMapping("/user/insert")
    public ResultVO<Object> insert(@RequestBody User user) {
        user.validate();
        userService.insert(user);
        return ResultVOUtil.success();
    }


}