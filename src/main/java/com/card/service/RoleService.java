package com.card.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.card.dao.RoleDao;
import com.card.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleService extends ServiceImpl<RoleDao, Role> {
    @Autowired
    private RoleService roleService;

    public List<Role> selectByParentId(Long id, Boolean contain) {
        /// todo 通过父级id查询下级的角色
        return null;
    }
}
