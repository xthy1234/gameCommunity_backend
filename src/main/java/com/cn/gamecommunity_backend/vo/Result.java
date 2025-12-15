package com.cn.gamecommunity_backend.vo;

import lombok.Data;

/**
 * &#064;description:  统一返回结果类
 * 将数据、状态码和消息封装在一起，使得前端可以统一处理响应
 * 通常是由控制器(controller)调用
 */

@Data
public class Result<T> {
    //状态码
    private Integer code;
    //提示消息
    private String message;
    //实际数据
    private T data;
/**
 * &#064;description:  成功，带数据地返回结果
 * @param data 数据
 * @return com.cn.gamecommunity_backend.vo.Result<T>
 */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * &#064;description:  成功，不带数据地返回结果
     * @return com.cn.gamecommunity_backend.vo.Result<T>
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * &#064;description:  失败，带数据地返回结果
     * @param code 状态码
     * @param message 提示消息
     * @return com.cn.gamecommunity_backend.vo.Result<T>
     */

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * &#064;description:  失败，不带数据地返回结果，返回值默认为500
     * @param message 提示消息
     * @return com.cn.gamecommunity_backend.vo.Result<T>
     */

    public static <T> Result<T> error(String message) {
        return error(500, message);
    }
}
