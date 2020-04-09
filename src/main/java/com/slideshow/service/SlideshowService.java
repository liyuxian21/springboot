package com.slideshow.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slideshow.dao.SlideshowDao;
import com.slideshow.entity.GoodsListVO;
import com.slideshow.entity.SlideshowInfo;
import com.slideshow.entity.SlideshowVO;
import com.util.AppResponse;
import com.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class SlideshowService {
    @Resource
    private SlideshowDao slideshowDao;

    /**
     * 新增轮播图
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addSlideshow(SlideshowInfo slideshowInfo) {
        int countProduct = slideshowDao.countProduct(slideshowInfo);
        if (0 != countProduct) {
            return AppResponse.bizError("新增商品已经存在，请重新选择！");
        }
        slideshowInfo.setSlideshowId(StringUtil.getCommonCode(2));
//        新增轮播图
        int count = slideshowDao.addSlideshow(slideshowInfo);
        if (0 == count) {
            return AppResponse.bizError("新增失败，请重试");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * 轮播图启用、禁用修改
     *
     * @param slideshowId
     * @param slideshowStatus
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse enableDisable(String slideshowId, String slideshowStatus) {
//        以逗号分隔开，批量选择
        List<String> listId = Arrays.asList(slideshowId.split(","));
        AppResponse appResponse = AppResponse.success("修改成功！");
//       修改状态
        int count = slideshowDao.enableDisable(listId, slideshowId, slideshowStatus);
        if (0 == count) {
            return appResponse = AppResponse.bizError("新增失败，请重试");
        }
        return appResponse;
    }

    /**
     * 查询轮播图列表
     *
     * @param slideshowVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listSlideshow(SlideshowVO slideshowVO) {
        PageHelper.startPage(slideshowVO.getPageNum(), slideshowVO.getPageSize());
        List<SlideshowVO> slideshowVOList = slideshowDao.listSlideshowByPage(slideshowVO);
//        包装对象
        PageInfo<SlideshowVO> pageData = new PageInfo<>(slideshowVOList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 删除轮播图
     *
     * @param slideshowId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteSlideshow(String slideshowId,String userId) {
//        将轮播图id以逗号分隔开
        List<String> listId = Arrays.asList(slideshowId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
//        删除轮播图
        int count = slideshowDao.deleteSlideshow(listId,userId);
        if (0 == count) {
            return AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;

    }

    /**
     * 选择轮播图查询商品
     * @param goodsListVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listSlideshowGoods(GoodsListVO goodsListVO) {
        PageHelper.startPage(goodsListVO.getPageNum(), goodsListVO.getPageSize());
        List<SlideshowVO> goodsListVoList = slideshowDao.goodsListVOyPage(goodsListVO);
//        包装对象
        PageInfo<SlideshowVO> pageData = new PageInfo<>(goodsListVoList);
        return AppResponse.success("查询成功！", pageData);

    }
}
