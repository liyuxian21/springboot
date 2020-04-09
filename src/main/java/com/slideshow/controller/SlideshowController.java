package com.slideshow.controller;

import com.slideshow.entity.GoodsListVO;
import com.slideshow.entity.SlideshowInfo;
import com.slideshow.entity.SlideshowVO;
import com.slideshow.service.SlideshowService;
import com.util.AppResponse;
import com.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 轮播图新增、查询、修改状态、查询操作
 * @author liyuxian
 * @time 2020-03-29
 */
@RestController
@RequestMapping("/slideshow")
public class SlideshowController {
    private static final Logger logger = LoggerFactory.getLogger(SlideshowController.class);
    @Resource
    private SlideshowService slideshowService;

    /**
     * 轮播图新增
     * @return
     */
    @PostMapping("addSlideshow")
    public AppResponse addSlideshow(SlideshowInfo slideshowInfo){
        try{
           return slideshowService.addSlideshow(slideshowInfo);
        }catch(Exception e){
            logger.error("新增失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 轮播图启用、禁用修改
     * @param slideshowId
     * @param slideshowStatus
     * @return
     */
    @PostMapping("enableDisable")
    public AppResponse enableDisable(String slideshowId,String slideshowStatus){
        try{
            return slideshowService.enableDisable(slideshowId,slideshowStatus);
        }catch(Exception e){
            logger.error("启用、禁用失败失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 查询轮播图列表
     * @param slideshowVO
     * @return
     */
    @PostMapping("listSlideshow")
    public AppResponse listSlideshow(SlideshowVO slideshowVO){
        try{
            return slideshowService.listSlideshow(slideshowVO);
        }catch(Exception e){
            logger.error("查询列表失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 删除轮播图
     * @param slideshowId
     * @return
     */
    @PostMapping("deleteSlideshow")
    public AppResponse deleteSlideshow(String slideshowId){
        try{
//            获取用户id
            String userId = AuthUtils.getCurrentUserId();
            return slideshowService.deleteSlideshow(slideshowId,userId);
        }catch(Exception e){
            logger.error("删除轮播图失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

    /**
     * 选择轮播图查询商品
     * @param goodsListVO
     * @return
     */
    @PostMapping("listSlideshowGoods")
    public AppResponse listSlideshow(GoodsListVO goodsListVO){
        try{
            return slideshowService.listSlideshowGoods(goodsListVO);
        }catch(Exception e){
            logger.error("查询商品失败",e);
            System.out.printf(e.toString());
            throw e;
        }
    }

}
