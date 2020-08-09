package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.entity.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    void updateById(@Param("id") Long id, @Param("user") User user);
}
