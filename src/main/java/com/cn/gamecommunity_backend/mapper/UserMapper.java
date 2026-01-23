package com.cn.gamecommunity_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.gamecommunity_backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from users where account = #{account}")
    User findByAccount(String account);

    @Select("select count(*) from users where account = #{account}")
    Integer countByAccount(String username);

    @Select("select count(*) from users where email = #{email}")
    Integer countByEmail(String email);


}

