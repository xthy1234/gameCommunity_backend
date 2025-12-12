package com.cn.gamecommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.gamecommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

