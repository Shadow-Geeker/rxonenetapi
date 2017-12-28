package com.qbssh.lyd.rxonenetapi.communication;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.qbssh.lyd.rxonenetapi.module.Protocol;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CONTENT_TYPE;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.JSON_CONTENT_TYPE;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.OCTET_STREAM;

/**
 * Api url condition builder.
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/17/2017.
 */

public class UrlConditionBuilder {

    public static final class HeadersMapBuilder {
        private String apiKey;

        public HeadersMapBuilder() {}

        public HeadersMapBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> map = new HashMap<>(2);
            if (this.apiKey == null) throw new IllegalArgumentException("api-key is null");
            map.put(API_KEY, this.apiKey);
            map.put(CONTENT_TYPE, JSON_CONTENT_TYPE);
            return map;
        }
    }

    public static final class DeviceFuzzyQueryBuilder {

        private String[] keyWords = null;
        private String authInfo = null;
        private String tag = null;
        private Boolean isOnline = null;
        private Boolean isPrivate = null;
        private int page = -1;
        private int perPage = -1;
        private String deviceId = null;
        private String begin = null;
        private String end = null;

        public DeviceFuzzyQueryBuilder() {
            super();
        }

        public DeviceFuzzyQueryBuilder keyWords(String... keyWords) {
            if (keyWords != null && keyWords.length > 0) {
                this.keyWords = keyWords;
            }
            return this;
        }

        public DeviceFuzzyQueryBuilder authInfo(String authInfo) {
            this.authInfo = authInfo;
            return this;
        }

        public DeviceFuzzyQueryBuilder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public DeviceFuzzyQueryBuilder isOnline(Boolean isOnline) {
            this.isOnline = isOnline;
            return this;
        }

        public DeviceFuzzyQueryBuilder isPrivate(Boolean isPrivate) {
            this.isPrivate = isPrivate;
            return this;
        }

        public DeviceFuzzyQueryBuilder page(int page) {
            this.page = page;
            return this;
        }

        public DeviceFuzzyQueryBuilder perPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public DeviceFuzzyQueryBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public DeviceFuzzyQueryBuilder begin(String begin) {
            this.begin = begin;
            return this;
        }

        public DeviceFuzzyQueryBuilder end(String end) {
            this.end = end;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> map = new HashMap<>();
            if (keyWords != null && keyWords.length > 0) {
                StringBuilder sb = new StringBuilder(keyWords[0]);
                int i = 1, size = keyWords.length;
                while (i < size) {
                    sb.append(",");
                    sb.append(keyWords[i]);
                    i++;
                }
                map.put("key_words", sb.toString());
            }
            if (authInfo != null) {
                map.put("auth_info", authInfo);
            }
            if (tag != null) {
                map.put("tag", tag);
            }
            if (isOnline != null) {
                map.put("online", isOnline.toString());
            }
            if (isPrivate != null) {
                map.put("private", isPrivate.toString());
            }
            if (page > 0) {
                map.put("page", String.valueOf(page));
            }
            if (perPage > 0) {
                map.put("per_page", String.valueOf(perPage));
            }
            if (deviceId != null) {
                map.put("device_id", deviceId);
            }
            if (begin != null) {
                map.put("begin", begin);
            }
            if (end != null) {
                map.put("end", end);
            }
            return map;
        }
    }

    public static final class DataPointQueryBuilder {

        public final static String DESC = "DESC";
        public final static String ASC = "ASC";

        private String[] dataStreamIds = null;
        private String start = null;
        private String end = null;
        private long duration = -1L;
        private int limit = -1;
        private String cursor = null;
        private Boolean newAdd = null;
        /**
         * Use {@link #ASC} or {@link #DESC}
         */
        private String sort = null;

        public DataPointQueryBuilder() {
            super();
        }

        /**
         * Format simple date to OneNet query date formation.
         * @param date date
         * @return formatted date string
         *
         */
        @SuppressLint("SimpleDateFormat")
        public static String toQueryDateFormat(Date date) {
            if (date == null) throw new IllegalArgumentException("date is null");
            StringBuilder sb = new StringBuilder();
            sb.append((new SimpleDateFormat("YYYY-MM-DDThh:mm:ss"))
                    .format(date));
            return sb.toString();
        }

        public static String toQueryDateFormat(Calendar calendar) {
            if (calendar == null)  throw new IllegalArgumentException("calendar is null");
            StringBuilder sb = new StringBuilder();
            sb.append((new SimpleDateFormat("YYYY-MM-DDThh:mm:ss", Locale.getDefault()))
                    .format(calendar.getTime()));
            return sb.toString();
        }

        public DataPointQueryBuilder dataStreamIds(String... ids) {
            if (ids != null && ids.length > 0) {
                dataStreamIds = ids;
            }
            return this;
        }

        public DataPointQueryBuilder start(String start) {
            this.start = start;
            return this;
        }

        public DataPointQueryBuilder end(String end) {
            this.end = end;
            return this;
        }

        public DataPointQueryBuilder duration(long duration) {
            this.duration = duration;
            return this;
        }

        public DataPointQueryBuilder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public DataPointQueryBuilder cursor(String cursor) {
            this.cursor = cursor;
            return this;
        }

        /**
         * Sort type.
         * @param sort {@link #DESC} or {@link #ASC}
         * @return self
         */
        public DataPointQueryBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }

        /**
         * If you contain no query parameter, that will result in long time delay to get the query
         * result. In this case, you can add this url parameter and make it true to solve the problem.
         * @param newAdd true or false
         * @return self
         */
        public DataPointQueryBuilder newAdd(boolean newAdd) {
            this.newAdd = newAdd;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> map = new HashMap<>();
            if (dataStreamIds != null && dataStreamIds.length > 0) {
                int i = 1, size = dataStreamIds.length;
                StringBuilder sb = new StringBuilder(dataStreamIds[0]);
                while (i < size) {
                    sb.append(",");
                    sb.append(dataStreamIds[i]);
                    i++;
                }
                map.put("datastream_id", sb.toString());
            }
            if (start != null) {
                map.put("start", start);
            }
            if (end != null) {
                map.put("end", end);
            }
            if (duration > 0) {
                map.put("duration", String.valueOf(duration));
            }
            if (limit > 0) {
                map.put("limit", String.valueOf(limit));
            }
            if (cursor != null) {
                map.put("cursor", cursor);
            }
            if (sort != null) {
                map.put("sort", sort);
            }
            if (newAdd != null) {
                map.put("newadd", newAdd.toString());
            }
            return map;
        }
    }

    public static final class TriggerFuzzyQueryBuilder {
        private String title = null;
        private int page = -1;
        private int perPage = -1;

        public TriggerFuzzyQueryBuilder() {
            super();
        }

        public TriggerFuzzyQueryBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TriggerFuzzyQueryBuilder page(int page) {
            this.page = page;
            return this;
        }

        public TriggerFuzzyQueryBuilder perPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> map = new HashMap<>();
            if (title != null) {
                map.put("title", title);
            }
            if (page > 0) {
                map.put("page", String.valueOf(page));
            }
            if (perPage > 0) {
                map.put("per_page", String.valueOf(perPage));
            }
            return map;
        }
    }

    public static final class ApiKeyQueryBuilder {
        private String key = null;
        private int page = -1;
        private int perPage = -1;
        private String deviceId = null;

        public ApiKeyQueryBuilder() {
            super();
        }

        public ApiKeyQueryBuilder key(String key) {
            this.key = key;
            return this;
        }

        public ApiKeyQueryBuilder page(int page) {
            this.page = page;
            return this;
        }

        public ApiKeyQueryBuilder key(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public ApiKeyQueryBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> map = new HashMap<>();
            if (key != null) {
                map.put("key", key);
            }
            if (page > 0) {
                map.put("page", String.valueOf(page));
            }
            if (perPage > 0) {
                map.put("per_page", String.valueOf(perPage));
            }
            if (deviceId != null) {
                map.put("device_id", deviceId);
            }
            return map;
        }
    }

    public static final class CommandQueryBuilder {

        private String deviceId;
        private boolean needResponse = false;
        private int timeout = 0;
        private int type = 0;
        private Protocol deviceProtocol;

        public CommandQueryBuilder deviceId(String deviceId) {
            if (deviceId == null || deviceId.length() == 0) {
                throw new IllegalArgumentException("Illegal device id");
            }
            this.deviceId = deviceId;
            return this;
        }

        /**
         * Need response. When the device protocol is EDP, this field is set only {@link #type} is
         * set to 0. For mqt device, it has no restriction. Other protocol can not set this field.
         * @param needResponse True if need, otherwise is false. Default is false.
         * @return CommandQueryBuilder self
         */
        public CommandQueryBuilder needResponse(boolean needResponse) {
            this.needResponse = needResponse;
            return this;
        }

        /**
         * Set timeout
         * @param timeout Use {@link java.util.concurrent.TimeUnit#SECONDS}, default is 0.
         * @return CommandQueryBuilder self
         */
        public CommandQueryBuilder timeout(int timeout) {
            if (timeout <= 0) timeout = 0;
            this.timeout = timeout;
            return this;
        }

        /**
         * Only EDP device can set this field.
         * @param type Command type. {@link UrlAddressPart.CommandType}
         * @return CommandQueryBuilder self
         *
         * @see UrlAddressPart.CommandType
         */
        public CommandQueryBuilder type(int type) {
            if (type != 0 && type != 1) throw new IllegalArgumentException("Illegal type");
            this.type = type;
            return this;
        }

        public CommandQueryBuilder protocol(Protocol protocol) {
            if (protocol == null) throw new NullPointerException("protocol cannot be null");
            this.deviceProtocol = protocol;
            return this;
        }

        public Map<String, String> build() {
            Map<String, String> map = new HashMap<>();
            if (deviceId == null || deviceId.length() == 0) throw new IllegalArgumentException("Illegal device");
            map.put(DEVICE_ID, deviceId);
            if (deviceProtocol == null) throw new NullPointerException("Device protocol is null");
            switch (deviceProtocol.id()) {
                case 0:
                    map.put("type", String.valueOf(type));
                case 1:
                    map.put("qos", needResponse ? "1" :"0");
                    map.put("timeout", String.valueOf(timeout));
                    break;
                default:
                    break;
            }
            return map;
        }

    }

    public static final class TCPQueryBuilder {
        private String[] names = null;

        public TCPQueryBuilder() {
            super();
        }

        public TCPQueryBuilder names(String... names) {
            if (names != null && names.length > 0) {
                this.names = names;
            }
            return this;
        }

        public String build() {
            if (names != null && names.length > 0) {
                StringBuilder sb = new StringBuilder(names[0]);
                int i = 1, len = names.length;
                while (i < len) {
                    sb.append(",");
                    sb.append(names[i]);
                    i++;
                }
                return sb.toString();
            } else {
                return "";
            }
        }
    }

    public static final class MultipartBodyBuilder {

        private MultipartBodyBuilder() {}

        public static MultipartBody.Part fileToPart(@NonNull String partName, @NonNull File file) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(OCTET_STREAM), file);
            return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
        }

        public static MultipartBody.Part bytesToPart(@NonNull String partName, @NonNull byte[] bytes) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(OCTET_STREAM), bytes);
            return MultipartBody.Part.create(requestBody);
        }

        public static MultipartBody.Part stringToPart(@NonNull String partName, @NonNull String body) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(JSON_CONTENT_TYPE), body);
            return MultipartBody.Part.create(requestBody);
        }

    }

}
