package com.example.mytraining.service;

import com.example.mytraining.MyTrainingApplication;
import com.example.mytraining.entity.Commodity;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest(classes = MyTrainingApplication.class)
@Slf4j
public class CommodityServiceTest {

    @Resource
    private CommodityService commodityService;

    @Test
    public void testPage(){
        PageInfo<Commodity> page = commodityService.page(new Commodity(), 10, 1);
        log.info("查询结果：{}",page.toString());
    }
}
