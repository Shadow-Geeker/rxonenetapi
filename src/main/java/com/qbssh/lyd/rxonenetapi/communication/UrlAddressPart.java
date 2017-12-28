package com.qbssh.lyd.rxonenetapi.communication;

/**
 * Url address constants
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/19/2017.
 */

public final class UrlAddressPart {

    final static String API_KEY = "api-key";
    final static String KEYS = "keys";
    final static String KEY_STRING = "key_string";
    final static String DEVICES = "devices";
    final static String DEVICE_ID = "device_id";
    final static String DEVICE_IDS = "devIds";
    final static String MQTT = "mqtt";
    final static String TOPIC = "topic";
    final static String TOPIC_DEIVCE = "topic_device";
    final static String DEVICE_TOPIC = "device_topic";
    final static String DATA_POINTS = "datapoints";
    final static String CMDS = "cmds";
    final static String CMD_UUID = "cmd_uuid";
    final static String BIN_DATA = "bindata";
    final static String INDEX = "index";
    final static String DATA_STREAMS = "datastreams";
    final static String DATA_STREAM_ID = "datastream_id";
    final static String DATA_STREAM_IDS = "datastream_ids";
    final static String DTU = "dtu";
    final static String PARSER = "parser";
    final static String PARSER_ID = "parser_id";
    final static String TRIGGERS = "triggers";
    final static String TRIGGER_ID = "trigger_id";

    final static String CONTENT_TYPE = "Content-Type";
    public final static String JSON_CONTENT_TYPE = "application/json; charset=utf-8";
    public final static String OCTET_STREAM = "application/octet-stream";


    enum RegisterProperty {
        ALLOW_DUP("\"allow_dup\": true"),
        DISALLOW_DUP("\"allow_dup\": false");

        private String value;

        RegisterProperty(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    enum DataPointDataType {
        FULL_JSON(1),
        TYPE_3(3),
        TYPE_4(4),
        TYPE_5(5);

        private int value;

        DataPointDataType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * Declare whether command needs response.
     */
    enum CommandResponse {
        CMD_NO_RESPONSE(0),
        CMD_NEED_RESPONSE(1);

        private int value;
        CommandResponse(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * Declare command type
     */
    enum CommandType {
        CMD_REQ(0),
        PUSH_DATA(1);

        private int value;
        CommandType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

}
