package com.cn.gamecommunity_backend.mapper;

import com.cn.gamecommunity_backend.entity.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface User1Mapper {

    @Select("select * from user1s")
    List<User1> findAll();

    @Select("select * from user1s where id = #{id}")
    User1 findById(Integer id);
}
