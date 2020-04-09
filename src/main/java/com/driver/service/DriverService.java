package com.driver.service;

import com.driver.dao.DriverDao;
import com.driver.entity.DriverDetailVo;
import com.driver.entity.DriverInfo;
import com.driver.entity.DriverListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.AppResponse;
import com.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DriverService {
    @Resource
    private DriverDao driverDao;

    /**
     * 新增司机
     * @param driverInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(DriverInfo driverInfo){
//        给司机id和司机编码和司机用户id设置随机数
        driverInfo.setDriverId(StringUtil.getCommonCode(2));
        driverInfo.setDriverCode(StringUtil.getCommonCode(2));
        driverInfo.setUserId(StringUtil.getCommonCode(2));
//        新增司机
        int count = driverDao.addDriver(driverInfo);
        int count2 = driverDao.addDriver2(driverInfo);
        if (0 == count){
            return AppResponse.bizError("新增失败，请重试！");
        }else if (0 == count2){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询司机详情
     * @param driverId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse findDriverDetailsById(String driverId) {
//        查询司机详情
        DriverDetailVo driverDetailVo=driverDao.findDriverDetailsById(driverId);
        return AppResponse.success("查询成功",driverDetailVo);

    }

    /**
     * 修改司机信息
     * @param driverInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriverById(DriverInfo driverInfo) {
//        修改司机
        int count = driverDao.updateDriverById(driverInfo);
        int count2 = driverDao.updateDriverById2(driverInfo);
        if (0 == count){
            return AppResponse.versionError("数据有变化，请刷新！");
        }else if (0 == count2){
            return AppResponse.versionError("数据有变化，请刷新！");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 查询司机列表
     * @param driverListVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listDriver(DriverListVo driverListVo){
        PageHelper.startPage(driverListVo.getPageNum(), driverListVo.getPageSize());
        List<DriverListVo> driverListVOList = driverDao.listDriver(driverListVo);
        //包装对象
        PageInfo<DriverListVo> pageData = new PageInfo<>(driverListVOList);
        return AppResponse.success("查询成功", pageData);
    }

    /**
     * 删除司机
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String userId,String userId1){
//        以逗号分隔开
        List<String> listId= Arrays.asList(userId.split(","));
        List<String> listId2= Arrays.asList(userId.split(","));
//        删除司机
        int count = driverDao.deleteDriver(listId,userId1);
        int count2 = driverDao.delete(listId2,userId1);
        if (0 == count){
            return AppResponse.bizError("删除失败，请重试！");
        }else if (0 == count2){
            return AppResponse.bizError("删除失败，请重试！");
        }
        return AppResponse.success("删除成功！");
    }
}
