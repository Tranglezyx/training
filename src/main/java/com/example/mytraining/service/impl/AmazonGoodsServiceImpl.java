package com.example.mytraining.service.impl;

import com.example.mytraining.util.HttpClientUtils;
import com.example.mytraining.entity.AmazonGoods;
import com.example.mytraining.mapper.AmazonGoodsMapper;
import com.example.mytraining.service.AmazonGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AmazonGoodsServiceImpl implements AmazonGoodsService {

    @Resource
    private AmazonGoodsMapper amazonGoodsMapper;

    @Override
    public void addAmazonGoods(String asin) {
        String asinUrl = "https://www.amazon.cn/dp/" + asin;
        String html = HttpClientUtils.getContentByUrl(asinUrl);
        log.info("获取到的html内容>>>>{}", html);
        Document parse = Jsoup.parse(html);
        String title = parse.body().getElementById("productTitle").text();
        String goodsPrice = parse.body().getElementsByClass("a-price-whole").get(0).text();
        String goodsPricePoint = parse.body().getElementsByClass("a-price-fraction").get(0).text();
        String picUrl = parse.body().getElementsByClass("a-dynamic-image").attr("src");
        AmazonGoods amazonGoods = AmazonGoods.builder()
                .goodsName(title)
                .goodsPrice(goodsPrice + goodsPricePoint)
                .picUrl(picUrl).build();
        amazonGoodsMapper.insert(amazonGoods);
    }
}
