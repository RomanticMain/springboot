package com.wtbu.day01springdemo.dao;

import com.wtbu.day01springdemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("SELECT * FROM t_user WHERE u_name=#{param1}")
    User queryUserByName(String name);

    @Insert("INSERT INTO t_user (u_name,u_password,u_addtime,u_email) value(#{u_name},#{u_password},now(),'tom@qq.com')")
    boolean registUser(User tempUser);
}
