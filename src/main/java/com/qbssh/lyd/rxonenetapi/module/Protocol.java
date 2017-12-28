package com.qbssh.lyd.rxonenetapi.module;

/**
 * Protocol type that OneNet provides.
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/16/2017.
 */

public enum Protocol {

    EDP(0, "EDP"),
    MQTT(1, "MQTT"),
    HTTP(2, "HTTP"),
    MODBUS(3, "MODBUS"),
    JTT808(4, "JT\\T808"),
    TCP(5, "TCP");

    private int id;
    private String protocol;

    Protocol(int id, String protocol) {
        this.id = id;
        this.protocol = protocol;
    }

    /**
     * Get protocol name.
     * @return protocol name
     */
    public String protocol() {
        return this.protocol;
    }

    /**
     * Get protocol id
     * @return protocol id
     */
    public int id() {
        return this.id;
    }
}
