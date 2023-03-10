package com.example.mytraining.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mytraining.BaseRequest;
import com.example.mytraining.BaseResult;
import com.example.mytraining.CommodityDTO;
import com.example.mytraining.entity.Commodity;
import com.example.mytraining.service.CommodityService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @PostMapping("/add")
    public Boolean add(@RequestBody Commodity commodity) {
        return commodityService.add(commodity);
    }

    @PostMapping("/delete")
    public Boolean delete(@RequestBody Long id) {
        return commodityService.remove(Wrappers.<Commodity>lambdaQuery()
                .eq(Commodity::getId, id));
    }

    @PostMapping("/batchDelete")
    public Boolean batchDelete(@RequestBody List<Long> ids) {
        return commodityService.removeByIds(ids);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody Commodity commodity) {
        return commodityService.update(commodity, Wrappers.<Commodity>lambdaUpdate()
                .eq(Commodity::getId, commodity.getId()));
    }

    @PostMapping("/batchUpdate")
    public Boolean batchUpdate(@RequestBody List<Commodity> commodityList) {
        return commodityService.updateBatchById(commodityList);
    }

    @PostMapping("/page")
    public BaseResult<PageInfo<Commodity>> page(@RequestBody CommodityDTO commodity) {
        return BaseResult.success(commodityService.page(commodity, commodity.getPageSize(), commodity.getPageNum()));
    }

    @PostMapping("/upload")
    public BaseResult<Boolean> upload(@RequestParam("file") MultipartFile multipartFile) {
        commodityService.upload(multipartFile);
        return BaseResult.success(true);
    }

    @PostMapping("/download")
    public void download(@RequestBody Commodity commodity, HttpServletResponse response) throws IOException {
        commodityService.download(commodity, response.getOutputStream());
    }

    @PostMapping("/progress")
    public BaseResult<BigDecimal> progress(@RequestBody BaseRequest request) {
        return BaseResult.success(commodityService.progress(request.getKey()));
    }

    @PostMapping("/startTask")
    public BaseResult<String> startTask() {
        return BaseResult.success(commodityService.startTask());
    }
}
