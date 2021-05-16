package com.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.user.dao.UserDao;
import com.user.entity.User;
import com.user.entity.UserDetail;
import com.user.entity.UserInfo;
import com.user.entity.UserList;
import com.util.AppResponse;
import com.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

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


    @Transactional(rollbackFor = Exception.class)
    public List<User> updateNew() {
        List<User> userList = userDao.updateNew();
        for (int i = 0; i<userList.size();i++){
            userList.get(i).setUserName(userList.get(i).getUserName());
            userList.get(i).setUserCode(userList.get(i).getUserCode());
            userList.get(i).setDeptName(userList.get(i).getDeptName());
            userList.get(i).setPingying(getPingYin(userList.get(i).getUserName()));
        }
        int count  = userDao.plInset(userList);
        if (count > 0){
            System.out.println("插入成功");
        }
        return userList;

    }

    public static String getPingYin(String str) {
        char[] t1 = null;
        t1 = str.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断能否为汉字?
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return t4.substring(0,1).toUpperCase();
    }
}
