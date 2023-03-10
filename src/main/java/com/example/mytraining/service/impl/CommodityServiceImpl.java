package com.example.mytraining.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mytraining.datasource.DynamicTableHolder;
import com.example.mytraining.entity.Commodity;
import com.example.mytraining.excel.listener.CommodityListener;
import com.example.mytraining.mapper.CommodityMapper;
import com.example.mytraining.service.CommodityService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Resource
    private RedisTemplate<String, BigDecimal> redisTemplate;
    @Resource
    private CommodityListener commodityListener;
    @Resource
    private TaskService taskService;

    @Override
    public PageInfo<Commodity> page(Commodity commodity, int pageSize, int pageNum) {
        PageMethod.startPage(pageNum, pageSize);
        commodity.setSuffix(DynamicTableHolder.getSuffix());
        List<Commodity> list = baseMapper.list(commodity);
        return new PageInfo<>(list);
    }

    @Override
    public void upload(MultipartFile multipartFile) {
        try {
            ExcelReaderBuilder read = EasyExcel.read(multipartFile.getInputStream(), Commodity.class, commodityListener)
                    .excelType(ExcelTypeEnum.XLSX);
            read.sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\doc\\training.xlsx");
        ExcelReaderBuilder reader = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            reader = EasyExcel.read(inputStream, Commodity.class,new CommodityListener())
                    .excelType(ExcelTypeEnum.XLSX);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        reader.sheet().doRead();
    }

    @Override
    public void download(Commodity commodity, OutputStream outputStream) {
        commodity.setSuffix(DynamicTableHolder.getSuffix());
        List<Commodity> list = baseMapper.list(commodity);
        EasyExcel.write(outputStream)
                .sheet("t_commodity")
                .head(Commodity.class)
                .doWrite(list);
    }

    @Override
    public BigDecimal progress(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String startTask() {
        String key = UUID.randomUUID().toString();
        taskService.startTask(key);
        return key;
    }

    @Override
    public Boolean add(Commodity commodity) {
        commodity.setSuffix(DynamicTableHolder.getSuffix());
        return baseMapper.add(commodity) > 0;
    }
}
