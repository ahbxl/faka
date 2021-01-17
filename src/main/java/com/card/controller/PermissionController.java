package com.card.controller;

import com.card.entity.vo.PermissionVO;
import com.card.entity.vo.Result;
import com.card.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/getById")
    public Result<Object> getById(@RequestBody PermissionVO permissionVO) {
        return Result.success(permissionService.getById(permissionVO.getId()));
    }
}
