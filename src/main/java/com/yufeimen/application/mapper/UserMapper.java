package com.yufeimen.application.mapper;

import com.yufeimen.application.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("SELECT * FROM USER WHERE username=#{name}")
    List<User> selectByName(String name);

}