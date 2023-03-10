package com.example.mytraining.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mytraining.entity.Commodity;

import java.util.List;

public interface CommodityMapper extends BaseMapper<Commodity> {

    int add(Commodity commodity);

    List<Commodity> list(Commodity commodity);
}
