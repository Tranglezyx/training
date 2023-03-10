package com.example.mytraining.datasource;

public class DynamicTableHolder {

    private static final ThreadLocal<Integer> TABLE_SOURCE_HOLDER = new ThreadLocal<>();

    public static Integer get() {
        return TABLE_SOURCE_HOLDER.get();
    }

    public static void remove() {
        TABLE_SOURCE_HOLDER.remove();
    }

    public static void set(Integer userId) {
        TABLE_SOURCE_HOLDER.set(userId);
    }

    public static String getSuffix() {
        if (get() % 2 == 0) {
            return "1";
        } else {
            return "";
        }
    }
}
