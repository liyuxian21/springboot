package com.store.controller;

import com.store.entity.ChinaVo;
import com.store.entity.StoreInfo;
import com.store.entity.StoreListVO;
import com.store.service.StoreService;
import com.util.AppResponse;
import com.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 门店CIUD
 * @author liyuxian
 * @time 2020-04-05
 */
@RestController
@RequestMapping("store")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    @Resource
    private StoreService storeService;

    /**
     * 门店新增
     * @param storeInfo
     * @return
     */
    @PostMapping("addStore")
    public AppResponse addStore(StoreInfo storeInfo){
        try{
            return storeService.addStore(storeInfo);
        }catch(Exception e){
            logger.error("新增失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

   /**
     * 查询门店详情
     * @param storeId
     * @return
     */
    @PostMapping("findStoreDetailsById")
    public AppResponse findStoreDetailsById(String storeId){
        try{
            return storeService.findStoreDetailsById(storeId);
        }catch(Exception e){
            logger.error("查询门店详情失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 省份下拉查询
     * @param chinaVo
     * @return
     */
    @PostMapping("provinceStoreList")
    public AppResponse provinceStoreList(ChinaVo chinaVo) {
        try {
            return storeService.provinceStoreList(chinaVo);
        } catch (Exception e) {
            logger.error("省份下拉查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 市下拉查询
     * @param chinaVo
     * @return
     */
    @PostMapping("cityStoreList")
    public AppResponse cityStoreList(ChinaVo chinaVo) {
        try {
            return storeService.cityStoreList(chinaVo);
        } catch (Exception e) {
            logger.error("城市下拉查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 区下拉查询
     * @param chinaVo
     * @return
     */
    @PostMapping("areaStoreList")
    public AppResponse areaStoreList(ChinaVo chinaVo) {
        try {
            return storeService.areaStoreList(chinaVo);
        } catch (Exception e) {
            logger.error("区下拉查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改门店
     * @param storeInfo
     * @return
     */
    @PostMapping("updateStoreById")
    public AppResponse updateStoreById(StoreInfo storeInfo){
        try{
            return storeService.updateStoreById(storeInfo);
        }catch(Exception e){
            logger.error("修改失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店列表
     * @param storeListVO
     * @return
     */
    @PostMapping("listStore")
    public AppResponse listStore(StoreListVO storeListVO){
        try{
            return storeService.listStore(storeListVO);
        }catch(Exception e){
            logger.error("查询失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 删除门店
     * @param storeId
     * @return
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore(String storeId){
        try{
//            获取用户id
            String userId = AuthUtils.getCurrentUserId();
            return storeService.deleteStore(storeId,userId);
        }catch(Exception e){
            logger.error("删除失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }
}
