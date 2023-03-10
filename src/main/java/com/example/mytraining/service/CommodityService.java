package com.example.mytraining.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mytraining.entity.Commodity;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.math.BigDecimal;

public interface CommodityService extends IService<Commodity> {

     PageInfo<Commodity> page(Commodity commodity, int pageSize, int pageNum);

     void upload(MultipartFile multipartFile);

     void download(Commodity commodity, OutputStream outputStream);

     BigDecimal progress(String key);

     String startTask();

     Boolean add(Commodity commodity);
}
