package com.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.dao.StoreDao;
import com.store.entity.ChinaVo;
import com.store.entity.StoreDetialVO;
import com.store.entity.StoreInfo;
import com.store.entity.StoreListVO;
import com.util.AppResponse;
import com.util.AuthUtils;
import com.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreService {
    @Resource
    private StoreDao storeDao;

    @Transactional(rollbackFor = Exception.class)
    public AppResponse addStore(StoreInfo storeInfo) {
//             统计店长用户id
        int countUserId = storeDao.countUserId(storeInfo);
//              统计门店电话
        int countPhone = storeDao.countPhone(storeInfo);
        if (0 != countUserId) {
            return AppResponse.bizError("新增店长用户已经存在，请重新选择！");
        } else if (0 != countPhone) {
            return AppResponse.bizError("新增联系电话已经存在，请重新选择！");
        }
//        给门店id和门店编号设置随机编号
        storeInfo.setStoreId(StringUtil.getCommonCode(2));
        storeInfo.setStoreCode(StringUtil.getCommonCode(2));
//               新增门店
        int count = storeDao.addStore(storeInfo);
        if (0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * 省份下拉查询
     * @param chinaVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse provinceStoreList(ChinaVo chinaVo) {
        List<ChinaVo> chinaVoList = storeDao.provinceStoreList(chinaVo);
        return AppResponse.success("查询成功！",chinaVoList);
    }
    /**
     * 城市下拉查询
     * @param chinaVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse cityStoreList(ChinaVo chinaVo) {
        List<ChinaVo> chinaVoList = storeDao.cityStoreList(chinaVo);
        return AppResponse.success("查询成功！",chinaVoList);
    }
    /**
     * 区下拉查询
     * @param chinaVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse areaStoreList(ChinaVo chinaVo) {
        List<ChinaVo> chinaVoList = storeDao.areaStoreList(chinaVo);
        return AppResponse.success("查询成功！",chinaVoList);
    }

    /**
     * 查询门店详情
     * @param storeId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse findStoreDetailsById(String storeId) {
//        查询门店详情
        StoreDetialVO storeDetialVO=storeDao.findStoreDetailsById(storeId);
        return AppResponse.success("查询成功",storeDetialVO);

    }

    /**
     * 修改门店
     * @param storeInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStoreById(StoreInfo storeInfo) {
//                    统计门店电话
        int countPhone2 = storeDao.countPhone2(storeInfo);
        if (0 != countPhone2) {
            return AppResponse.bizError("修改电话已经存在，请重新选择！");
        }
//        修改门店
        int count = storeDao.updateStoreById(storeInfo);
        if (0 == count){
            return AppResponse.versionError("数据有变化，请刷新！");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 查询门店列表
     * @param storeListVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listStore(StoreListVO storeListVO){
        PageHelper.startPage(storeListVO.getPageNum(), storeListVO.getPageSize());
        List<StoreListVO> storeListVOList = storeDao.listStore(storeListVO);
        //包装对象
        PageInfo<StoreListVO> pageData = new PageInfo<>(storeListVOList );
        return AppResponse.success("查询成功", pageData);
    }

    /**
     * 删除门店
     * @param storeId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeId,String userId){
//        以逗号分隔开
        List<String> listId= Arrays.asList(storeId.split(","));
//        删除门店
        int count = storeDao.deleteStore(listId,userId);
        if (0 == count){
            return AppResponse.bizError("删除失败，请重试！");
        }
        return AppResponse.success("删除成功！");
    }

}
