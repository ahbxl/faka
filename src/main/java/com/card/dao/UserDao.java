package com.card.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.card.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    void updateById(@Param("id") Long id, @Param("user") User user);

    void deleteByIds(@Param("ids") List<Long> ids);
}
