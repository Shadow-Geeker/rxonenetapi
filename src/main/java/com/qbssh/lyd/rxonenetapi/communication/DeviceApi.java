package com.qbssh.lyd.rxonenetapi.communication;

import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;
import com.qbssh.lyd.rxonenetapi.module.Protocol;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DATA_POINTS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICES;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_IDS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.RegisterProperty;

/**
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/16/2017.
 *
 * @see <a href="https://open.iot.10086.cn/doc/art262.html#68" />
 */
public interface DeviceApi {

    /**
     * Register device for {@link Protocol#EDP}, {@link Protocol#MQTT}, {@link Protocol#HTTP},
     * {@link Protocol#MODBUS} and {@link}.
     * @param registerCode 16 bits device register code
     * @param registerJson register json. The json is like
     *                     {
     *                          "sn": "...",
     *                          "title": "" (optional)
     *                     }
     *                     or
     *                     {
     *                          "mac": "..." (device mac address)
     *                          "title": "..." (optional)
     *                     }
     * @return A {@link Observable} object with generic type.
     */
    @POST("register_de")
    Observable<OneNetResponse> registerDevice(@Query("register_code") String registerCode,
                                              @Body String registerJson);

    /**
     * Update device register property "allow_dup". It is either {@link RegisterProperty#ALLOW_DUP}
     * or {@link RegisterProperty#DISALLOW_DUP}.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param json {@link RegisterProperty#ALLOW_DUP} or {@link RegisterProperty#DISALLOW_DUP}
     * @return A {@link Observable} object with generic type.
     */
    @PUT("register_attr")
    Observable<OneNetResponse> updateDeviceRegisterProperty(@HeaderMap Map<String, String> headers,
                                                            @Body String json);

    /**
     * Add a new device.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param json device content.
     * @return A {@link Observable} object with generic type.
     */
    @POST(DEVICES)
    Observable<OneNetResponse> addDevice(@HeaderMap Map<String, String> headers,
                                         @Body String json);

    /**
     * Update a device.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId master or device id
     * @param json device content
     * @return A {@link Observable} object with generic type.
     */
    @PUT(DEVICES + "/{" + DEVICE_ID + "}")
    Observable<OneNetResponse> updateDevice(@HeaderMap Map<String, String> headers,
                                            @Path(DEVICE_ID) String deviceId,
                                            @Body String json);

    /**
     * Query a single device content.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id
     * @return A {@link Observable} object with generic type.
     */
    @GET(DEVICES + "/{" + DEVICE_ID + "}")
    Observable<OneNetResponse> queryDevice(@HeaderMap Map<String, String> headers,
                                           @Path(DEVICE_ID) String deviceId);

    /**
     * Fuzzy query devices by conditions.
     * @param apiKey Master api key
     * @param map Condition map. Use {@link UrlConditionBuilder.DeviceFuzzyQueryBuilder}
     *            to build the map.
     * @return A {@link Observable} object with generic type.
     */
    @GET(DEVICES)
    Observable<OneNetResponse> fuzzyQueryDevices(@Header(API_KEY) String apiKey,
                                                 @QueryMap(encoded = true) Map<String, String> map);

    /**
     * Query devices status.
     * @param apiKey master api key
     * @param deviceIds device ids, using ',' to separate the device id.
     * @return A {@link Observable} object with generic type.
     */
    @GET(DEVICES + "/status")
    Observable<OneNetResponse> queryDevicesStatus(@Header(API_KEY) String apiKey,
                                                  @Query(DEVICE_IDS) String deviceIds);

    /**
     * Query devices data points.
     * @param apiKey master or device api key
     * @param deviceIds devices ids
     * @return A {@link Observable} object with generic type.
     */
    @GET(DEVICES + "/" + DATA_POINTS)
    Observable<OneNetResponse> queryDevicesDataPoints(@Header(API_KEY) String apiKey,
                                                      @Query(DEVICE_IDS) String deviceIds);

    /**
     * Delete a device.
     * @param apiKey device api key
     * @param deviceId device id
     * @return A {@link Observable} object with generic type.
     */
    @GET(DEVICES + "/{" + DEVICE_ID + "}")
    Observable<OneNetResponse> deleteDevice(@Header(API_KEY) String apiKey,
                                            @Path(DEVICE_ID) String deviceId);
}
