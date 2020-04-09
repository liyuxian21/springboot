package com.goodsClass.controller;

import com.goodsClass.entity.GoodsClassDetail;
import com.goodsClass.entity.GoodsClassInfo;
import com.goodsClass.entity.GoodsClassList;
import com.goodsClass.service.GoodsClassService;
import com.util.AppResponse;
import com.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类增删改查
 *
 * @author liyuxian
 * @time 2020-03-26
 */
@RestController
@RequestMapping("/goodsClass")
public class GoodsClassController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsClassController.class);

    @Resource
    private GoodsClassService goodsClassService;

    /**
     * 新增商品
     *
     * @author liyuxia
     * @time 2020-03-24
     */
    @PostMapping("addGoodsClass")
    public AppResponse addGoodsClass(GoodsClassInfo goodsClassInfo,String parentClassCode) {
        try {
            //获取用户id
            String userId = AuthUtils.getCurrentUserId();
            goodsClassInfo.setCreatePeople(userId);
            AppResponse appResponse = goodsClassService.addGoodsClass(goodsClassInfo,parentClassCode);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品分类新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品详情
     * @author liyuxian
     * @time 2020-03-27
     * @param classId
     * @return
     */
    @PostMapping("findGoodsClassById")
    public AppResponse findGoodsClassById(String classId){
        try{
//            获取用户id
            return  goodsClassService.findGoodsClassById(classId);
        }catch(Exception e){
            logger.error("商品分类详情查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品分类
     * @param goodsClassInfo
     * @return
     */
    @PostMapping("updateGoodsClassById")
    public  AppResponse updateGoodsClassById(GoodsClassInfo goodsClassInfo){
        try{
//            获取用户id
            String userId = AuthUtils.getCurrentUserId();
            goodsClassInfo.setUpdatePeople(userId);
            return goodsClassService.updateGoodsClassById(goodsClassInfo);
        }catch(Exception e){
            logger.error("修改商品分类失败",e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品列表
     * @param goodsClassList
     * @return
     */
    @PostMapping("listGoodsClass")
    public  AppResponse listGoodsClass(GoodsClassList goodsClassList){
        try{
            return goodsClassService.selectAllMenus(goodsClassList);
        }catch(Exception e){
            logger.error("查询列表错误我",e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 删除商品分类
     * @return
     */
    @PostMapping("deleteGoodsClass")
    public AppResponse deleteGoodsClass(String classId){
        try{
            //获取删除用户id
            String userId = AuthUtils.getCurrentUserId();
            return goodsClassService.deleteGoodsClasss(classId,userId);
        }catch(Exception e){
            logger.error("删除商品分类错误",e);
            System.out.println(e.toString());
            throw e;
        }
    }


}
