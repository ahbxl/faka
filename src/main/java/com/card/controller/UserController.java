package com.card.controller;

import com.card.command.user.UserCommand;
import com.card.entity.domain.User;
import com.card.entity.vo.ResultVO;
import com.card.service.UserService;
import com.card.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public ResultVO<Object> findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody UserCommand command) {
        return ResultVOUtil.success(userService.findByPage(pageNum, pageSize, command));
    }

    @PostMapping("/updateById/{id}")
    public ResultVO<Object> updateById(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateById(id, user);
        return ResultVOUtil.success();
    }

    @PostMapping("/deleteById/{id}")
    public ResultVO<Object> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResultVOUtil.success();
    }

    @PostMapping("/product/insert")
    public ResultVO<Object> insert(@RequestBody User user) {
        user.validate();
        userService.insert(user);
        return ResultVOUtil.success();
    }
}