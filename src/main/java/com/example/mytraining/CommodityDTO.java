package com.example.mytraining;

import com.example.mytraining.entity.Commodity;
import lombok.Data;

@Data
public class CommodityDTO extends Commodity {

    private int pageSize;
    private int pageNum;
}
