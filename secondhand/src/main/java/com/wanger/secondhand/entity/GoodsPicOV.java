package com.wanger.secondhand.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsPicOV {
    private Goods goods;

    private List<Picture> pictures;

    public GoodsPicOV(Goods goods, List<Picture> pictures) {
        this.goods = goods;
        this.pictures = pictures;
    }
}
