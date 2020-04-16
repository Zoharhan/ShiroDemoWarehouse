package com.zohar.demo.enums;

/**
 * 用户会话状态
 *
 * @author zohar
 */
public enum MyShiroStatus {
    /**
     * 用户状态
     */
    on_line("在线"), off_line("离线");

    private final String info;

    private MyShiroStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
