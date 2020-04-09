package com.slideshow.dao;

import com.slideshow.entity.GoodsListVO;
import com.slideshow.entity.SlideshowInfo;
import com.slideshow.entity.SlideshowVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SlideshowDao {
    /**
     * 新增轮播图
     * @param slideshowInfo
     * @return
     */
    int addSlideshow(SlideshowInfo slideshowInfo);

    /**
     * 统计商品id数量
     * @param slideshowInfo
     * @return
     */
    int countProduct(SlideshowInfo slideshowInfo);

    /**
     * 修改状态
     * @param slideshowId
     * @param slideshowStatus
     * @return
     */
    int enableDisable(List<String> listId, String slideshowId, String slideshowStatus);

    /**
     * 查询轮播图列表
     * @param slideshowVO
     * @return
     */
    List<SlideshowVO> listSlideshowByPage(SlideshowVO slideshowVO);
    /**
     * 删除轮播图
     * @param listId
     * @return
     */
    int deleteSlideshow(List<String> listId, String userId);

    /**
     * 查询商品
     * @param goodsListVO
     * @return
     */
    List<SlideshowVO> goodsListVOyPage(GoodsListVO goodsListVO);
}
