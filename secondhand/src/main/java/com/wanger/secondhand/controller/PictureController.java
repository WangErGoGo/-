package com.wanger.secondhand.controller;



import com.wanger.secondhand.entity.Goods;
import com.wanger.secondhand.entity.Picture;
import com.wanger.secondhand.service.GoodsService;
import com.wanger.secondhand.service.PictureService;
import com.wanger.secondhand.util.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wanger
 * @since 2020-12-04
 */
@RestController
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("upload");
    }


    @PostMapping(value = "/up")

    public MyMessage upload(HttpServletRequest request,@RequestParam("userid") String userId,@RequestParam("type") String type
            ,@RequestParam("describe") String describe) {

        MultipartHttpServletRequest mhsr = ((MultipartHttpServletRequest) request);
        List<MultipartFile> list = mhsr.getFiles("images");

        if (list.size() != 0) {
            Picture picture = new Picture();
            Goods goods = new Goods();
            goods.setType(type);
            goods.setDescribe(describe);
            goods.setUserid(userId);
            goodsService.getBaseMapper().insert(goods);
            picture.setGoodsId(goods.getId());
            int index = 0;
            for (MultipartFile f : list) {
                String name;
                if (!(name = PictureUtil.uploadImage(f)).equals("")) {
                    picture.setPlace(index++);
                    picture.setPath(name);
                    pictureService.getBaseMapper().insert(picture);
                }
            }
            return new MyMessage(0, "成功发布商品");
        }
        return  new MyMessage(-1, "发布商品失败");
    }


    @GetMapping("/getimage/{name}")
    public void get(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
        PictureUtil.getImage(response, name);
    }

}

