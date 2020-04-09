package com.goodsHot.dao;

import com.goodsHot.entity.GoodsHotDetailVO;
import com.goodsHot.entity.GoodsHotInfo;
import com.goodsHot.entity.GoodsHotListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsHotDao {
    /**
     * 统计商品id
     * @param goodsHotInfo
     * @return
     */
    int countProduct(GoodsHotInfo goodsHotInfo);

    /**
     * 新增热门商品
     * @param goodsHotInfo
     * @return
     */
    int addGoodsHot(GoodsHotInfo goodsHotInfo);

    /**
     * 查询热门商品详情
     * @param goodsHotId
     * @return
     */
    GoodsHotDetailVO findGoodsHotById(String goodsHotId);

    /**
     * 修改热门商品
     * @param goodsHotInfo
     * @return
     */
    int updateGoodsHotById(GoodsHotInfo goodsHotInfo);

    /**
     * 删除热门商品
     * @param listId
     * @param userId
     * @return
     */
    int deleteGoodsHot(List<String> listId, String userId);

    /**
     * 查询热门商品列表
     * @param goodsHotListVo
     * @return
     */
    List<GoodsHotListVo> listGoodsHot(GoodsHotListVo goodsHotListVo);

    /**
     * 展示商品数量设置
     * @param number
     * @return
     */
    int setGoodsHot(String number);
}
