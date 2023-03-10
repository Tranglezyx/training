package com.example.mytraining.controller;

import com.example.mytraining.service.AmazonGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MyController {

    @Resource
    private AmazonGoodsService amazonGoodsService;

    @GetMapping("/hello")
    public String helloWorld() {
        log.info("hello world");
        return "hello world";
    }

    @GetMapping("/getAsinHtml/{asin}")
    public void getAsinListing(@PathVariable String asin) {
        amazonGoodsService.addAmazonGoods(asin);
    }
}
