package com.user.service;


import com.user.dao.UserDao;
import com.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {
    @Resource
    private UserDao userDao;

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
