package com.example.mytraining.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.example.mytraining.datasource.DynamicTableHolder;
import com.example.mytraining.entity.Commodity;
import com.example.mytraining.mapper.CommodityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CommodityListener implements ReadListener<Commodity> {

    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public void invoke(Commodity commodity, AnalysisContext analysisContext) {
        log.info("entity >>> {} ", commodity.toString());
        commodity.setSuffix(DynamicTableHolder.getSuffix());
        if (commodityMapper != null) {
            commodityMapper.add(commodity);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("解析完成 >>> ");
    }
}
