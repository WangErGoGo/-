package com.wanger.secondhand.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanger.secondhand.entity.Goods;
import com.wanger.secondhand.entity.GoodsPicOV;
import com.wanger.secondhand.entity.Picture;
import com.wanger.secondhand.service.GoodsService;
import com.wanger.secondhand.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wanger
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PictureService pictureService;

    @GetMapping("/{current}/{size}")
    public List<GoodsPicOV>  getGoods(@PathVariable("current") int  current,@PathVariable("size") int  size){

        IPage<Goods> goodsPage = new Page<>(current, size);//参数一是当前页，参数二是每页个数
        goodsPage = goodsService.getBaseMapper().selectPage(goodsPage, null);
        List<GoodsPicOV> goodsPicOVList = new ArrayList<>(((int) goodsPage.getTotal()));
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        for (Goods g:goodsPage.getRecords()) {
            queryWrapper.eq("goods_id", g.getId());
            GoodsPicOV goodsPicOV = new GoodsPicOV(g, pictureService.list(queryWrapper));
            goodsPicOVList.add(goodsPicOV);
            queryWrapper.clear();
        }

        return goodsPicOVList;

    }



}

