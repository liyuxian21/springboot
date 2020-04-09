package com.goodsClass.entity;


import java.util.List;
/**
 * 一级分类集合实体类
 * @author liyuxian
 * @time 2020-04-08
 */
public class GoodsClassList {
    /**
     * 商品分类id
     */
    private String classId;
    /**
     * 商品分类名称
     */
    private String className;
    /**
     * 商品分类列表子节点
     */
    private List<GoodsClassList> secondGoodsClassList;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<GoodsClassList> getSecondGoodsClassList() {
        return secondGoodsClassList;
    }

    public void setSecondGoodsClassList(List<GoodsClassList> secondGoodsClassList) {
        this.secondGoodsClassList = secondGoodsClassList;
    }
}
