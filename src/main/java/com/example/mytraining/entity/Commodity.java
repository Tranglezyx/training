package com.example.mytraining.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_commodity")
public class Commodity {

    @TableId(type = IdType.AUTO)
    @ExcelIgnore
    private Long id;
    @ExcelProperty(value = "价格")
    private BigDecimal price;
    @ExcelProperty(value = "名称")
    private String name;
    @ExcelProperty(value = "sku")
    private String sku;
    @ExcelProperty(value = "库存")
    private Integer num;
    @ExcelProperty(value = "描述")
    private String description;

    @TableField(exist = false)
    @ExcelIgnore
    private String suffix;
}
