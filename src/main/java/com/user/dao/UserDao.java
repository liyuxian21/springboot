package com.user.dao;

import com.user.entity.User;
import com.user.entity.UserDetail;
import com.user.entity.UserInfo;
import com.user.entity.UserList;
import com.user.service.UserService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserDAo
 *
 * @author liyuxian
 * @time 020-03-25
 */
@Mapper
public interface UserDao {
    /**
     * 统计用户账户
     *
     * @return
     */
    int countUserAccount(UserInfo userInfo);

    /**
     * 统计手机号
     *
     * @return
     */
    int countUserPhone(UserInfo userInfo);

    /**
     * 新增用户
     *
     * @return
     */
    int addUser(UserInfo userInfo);

    /**
     * 修改用户
     *
     * @return
     */
    int updateUserById(UserInfo userInfo);

    /**
     * 删除用户
     *
     * @return
     */
    int deleteUser(List<String> listId, String userId1);

    /**
     * 查询用户详情
     *
     * @return
     */
    UserDetail findUserById(String userId);

    /**
     * 查询用户列表
     *
     * @return
     */
    List<UserList> listUserByPage(UserList userList);

    List<User>  updateNew();

    int plInset(List<User> userList);
}
