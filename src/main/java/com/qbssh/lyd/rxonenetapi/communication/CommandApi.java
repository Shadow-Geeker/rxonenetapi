package com.qbssh.lyd.rxonenetapi.communication;


import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CMDS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CMD_UUID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CONTENT_TYPE;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CommandResponse;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CommandType;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;


/**
 * OneNet command api.
 * Note: Be careful to use this api because it has no check of the parameters that meet to OneNet's
 * logical request, such as the command format of MODBUS device and so on. You must check by yourself.
 *
 * @author LYD
 * @version 0.1
 *
 * @see <a href="https://open.iot.10086.cn/doc/art257.html#68" />
 */
public interface CommandApi {

    /**
     * Send command to the device which uses EDP protocol.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id. Stands for the "device_id" url argument.
     * @param needResponse Stands for "qos" url argument. The value is {@link CommandResponse#CMD_NEED_RESPONSE}
     *                     or {@link CommandResponse#CMD_NO_RESPONSE}
     * @param timeout Stands for "timeout" url argument. The time unit is
     *                  {@link java.util.concurrent.TimeUnit#SECONDS}
     * @param type Stands for "type" url argument. The value is {@link CommandType#CMD_REQ} or
     *             {@link CommandType#PUSH_DATA}
     * @param cmd The command that you want to send to your device.
     * @return An {@link Observable} object with generic type.
     */
    @POST(CMDS)
    Observable<OneNetResponse> sendCommandToEDP(@HeaderMap Map<String, String> headers,
                                                @Query(DEVICE_ID) String deviceId,
                                                @Query("qos") int needResponse,
                                                @Query("timeout") int timeout,
                                                @Query("type") int type,
                                                @Body String cmd);

    /**
     * Send command to the device which uses MQTT protocol.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id. Stands for the "device_id" url argument.
     * @param needResponse Stands for "qos" url argument. The value is {@link CommandResponse#value()}
     * @param timeout Stands for "timeout" url argument. The time unit is {@link CommandType#value()}
     * @param cmd The command that you want to send to your device.
     * @return An {@link Observable} object with generic type.
     */
    @POST(CMDS)
    Observable<OneNetResponse> sendCommandToMQTT(@HeaderMap Map<String, String> headers,
                                                 @Query(DEVICE_ID) String deviceId,
                                                 @Query("qos") int needResponse,
                                                 @Query("timeout") int timeout,
                                                 @Body String cmd);

    /**
     * Send command to the device which uses MODBUS protocol or TCP protocol or other devices use
     * other protocol.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id. Stands for the "device_id" url argument.
     * @param cmd The command that you want to send to your device.
     *            Note: If the device uses MODBUS protocol, the cmd param must be json-type and the
     *            format is {"cmd":"..."}.
     * @return An {@link Observable} object with {@link OneNetResponse} type.
     *
     * @see <a href="https://open.iot.10086.cn/doc/art257.html#68">
     */
    @POST(CMDS)
    Observable<OneNetResponse> sendCommand(@HeaderMap Map<String, String> headers,
                                           @Query(DEVICE_ID) String deviceId,
                                           @Body String cmd);

    /**
     * Send command to MQTT or EDP device.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param map Query restrictions, use {@link UrlConditionBuilder.CommandQueryBuilder} to build
     *            a query map.
     * @param cmd The command that you want to send to your device.
     * @return An {@link Observable} object with generic type.
     */
    @POST(CMDS)
    Observable<OneNetResponse> sendCommand(@HeaderMap Map<String, String> headers,
                                           @QueryMap Map<String, String> map,
                                           @Body String cmd);

    /**
     * Query command status with command UUID.
     * @param deviceApiKey Master api key
     * @param cmdUuid Uuid from response of sending the same command
     * @return An {@link Observable} object with generic type.
     */
    @GET(CMDS + "/{" + CMD_UUID + "}")
    Observable<OneNetResponse> queryCommandStatus(@Header(API_KEY) String deviceApiKey,
                                                  @Path(CMD_UUID) String cmdUuid);

    /**
     * Query command response with command UUID.
     * @param deviceApiKey Master api key
     * @param cmdUuid Uuid from response of sending the same command
     * @return An {@link Observable} object with generic type.
     */
    @GET(CMDS + "/{" + CMD_UUID + "}/resp")
    Observable<String> queryCommandResponse(@Header(API_KEY) String deviceApiKey,
                                            @Path(CMD_UUID) String cmdUuid);

}
