package com.qbssh.lyd.rxonenetapi.communication;


import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;
import com.qbssh.lyd.rxonenetapi.communication.UrlConditionBuilder.DataPointQueryBuilder;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CONTENT_TYPE;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DATA_POINTS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICES;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DataPointDataType;



/**
 * Data point api. Note that this interface doesn't prepare for Android to use because to send data
 * point or update data is the device's responsibility. But if you want user to customize or you have
 * the requirements, you can use this api.
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/16/2017.
 *
 * @see <a href="https://open.iot.10086.cn/doc/art260.html#68" />
 */

public interface DataPointApi {

    /**
     * Add a new data point.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id
     * @param type Sending type. Can be {@link DataPointDataType#FULL_JSON},
     * {@link DataPointDataType#TYPE_3}, {@link DataPointDataType#TYPE_4}, {@link DataPointDataType#TYPE_5}
     * @param json data point json.
     * @return A {@link Observable} object with generic type.
     */
    @POST(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_POINTS)
    Observable<OneNetResponse> addDataPoint(@HeaderMap Map<String, String> headers,
                                            @Path(DEVICE_ID) String deviceId,
                                            @Query("type") int type,
                                            @Body String json);

    /**
     * Query data points by conditions.
     * @param apiKey Master or device api key
     * @param deviceId Device id
     * @param map Url query parameter map.Use {@link DataPointQueryBuilder}
     *            to build a query map.
     * @return A {@link Observable} object with generic type.
     */
    @GET(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_POINTS)
    Observable<OneNetResponse> queryDataPoints(@Header(API_KEY) String apiKey,
                                               @Path(DEVICE_ID) String deviceId,
                                               @QueryMap(encoded = true) Map<String, String> map);
}
