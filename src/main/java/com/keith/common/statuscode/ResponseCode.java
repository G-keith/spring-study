package com.keith.common.statuscode;

/**
 * 返回成功失败标识
 * @author keith
 * @version 1.0
 * @date 2019/10/28
 */
public enum ResponseCode {

    /**
     * 执行成功
     */
    SUCCESS(0, "SUCCESS"),

    /**
     * 执行失败
     */
    ERROR(1, "ERROR");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
