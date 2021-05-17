package com.user.dao;

import com.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDao {

    List<User>  updateNew();

    int plInset(List<User> userList);
}
