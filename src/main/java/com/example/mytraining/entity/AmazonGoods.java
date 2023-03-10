package com.example.mytraining.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("amazon_goods")
public class AmazonGoods {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String goodsName;
    private String goodsPrice;
    private String picUrl;
}
