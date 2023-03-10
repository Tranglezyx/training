package com.example.mytraining.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.mytraining.datasource.DynamicDataSource;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    private static Map<Integer, DataSourceInfo> INFO_MAP = new HashMap<>();

    static {
        DataSourceInfo info = new DataSourceInfo();
        info.setUrl("jdbc:mysql://Trangle:3306/my_training1?useUnicode=true&characterEncoding=utf-8&generateSimpleParameterMetadata=true&useSSL=false&allowMultiQueries=true&useAffectedRows=true");
        info.setUsername("root");
        info.setPassword("Trangle");
        INFO_MAP.put(2, info);
    }

    @Bean
    public DruidDataSource defaultDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://Trangle:3306/my_training?useUnicode=true&characterEncoding=utf-8&generateSimpleParameterMetadata=true&useSSL=false&allowMultiQueries=true&useAffectedRows=true");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Trangle");
        return dataSource;
    }

    @Bean
    @Primary
    public DataSource dataSource(DruidDataSource defaultDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(1, defaultDataSource);
        INFO_MAP.forEach((key, value) -> {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(value.getUrl());
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername(value.getUsername());
            dataSource.setPassword(value.getPassword());
            targetDataSource.put(key, dataSource);
        });
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

    @Data
    public static class DataSourceInfo {
        private String url;
        private String username;
        private String password;
    }
}
