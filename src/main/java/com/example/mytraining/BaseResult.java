package com.example.mytraining;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {

    private Integer code;

    private T data;

    private String message;

    public static BaseResult success(Object data) {
        return new BaseResult(0, data, null);
    }

    public static BaseResult fail(String message) {
        return new BaseResult(500, null, message);
    }
}
