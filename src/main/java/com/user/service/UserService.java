package com.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.user.dao.UserDao;
import com.user.entity.UserDetail;
import com.user.entity.UserInfo;
import com.user.entity.UserList;
import com.util.AppResponse;
import com.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 实现类
 * 用户增删改查
 *
 * @author liyuxian
 * @time 020-03-25
 */

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 新增用户
     *
     * @author liyuxian
     * @time 020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(UserInfo userInfo) {
//        判断用户账户是否存在
        int countUserAccount = userDao.countUserAccount(userInfo);
        int countUserPhone = userDao.countUserPhone(userInfo);
        if (0 != countUserAccount) {
            return AppResponse.bizError("用户账户已存在，请重新输入！");
        } else if (0 != countUserPhone) {
            return AppResponse.bizError("手机号已存在，请重新输入！");
        }
        userInfo.setUserId(StringUtil.getCommonCode(2));
        int count = userDao.addUser(userInfo);
        if (0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");

    }

    /**
     * 修改用户
     *
     * @author liyuxian
     * @time 020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUserById(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功！");
//        判断用户账户是否存在
        int countUserAccount = userDao.countUserAccount(userInfo);
        if (0 != countUserAccount) {
            return AppResponse.bizError("用户账户已存在，请重新输入！");
        }
        // 用户修改
        int count = userDao.updateUserById(userInfo);
        if (0 == count) {
            return appResponse = AppResponse.versionError("数据有变化，请刷新！");
        }
        return appResponse;
    }

    /**
     * 删除用户
     *
     * @author liyuxian
     * @time 020-03-25
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userId, String userId1) {
        List<String> listId = Arrays.asList(userId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
//        删除用户
        int count = userDao.deleteUser(listId, userId1);
        if (0 == count) {
            return AppResponse.bizError("删除失败，请重试！");
        }
        return AppResponse.success("删除成功！");

    }

    /**
     * 查询用户详情
     *
     * @author liyuxian
     * @time 020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse findUserById(String userId) {
        //查询用户
        UserDetail userInfo = userDao.findUserById(userId);
        return AppResponse.success("查询成功！", userInfo);
    }

    /**
     * 查询用户列表
     *
     * @author liyuxian
     * @time 020-03-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listUser(UserList userList) {
        PageHelper.startPage(userList.getPageNum(), userList.getPageSize());
        List<UserList> userListList = userDao.listUserByPage(userList);
        //包装对象
        PageInfo<UserList> pageData = new PageInfo<>(userListList);
        return AppResponse.success("查询成功", pageData);

    }
}
