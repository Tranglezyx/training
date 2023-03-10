package com.example.mytraining.datasource;

public class DataSourceContextHolder {

    private static final ThreadLocal<Integer> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    public static Integer getDataSource() {
        return DATA_SOURCE_HOLDER.get();
    }

    public static void remove() {
        DATA_SOURCE_HOLDER.remove();
    }

    public static void setDataSource(Integer userId) {
        DATA_SOURCE_HOLDER.set(userId);
    }
}
