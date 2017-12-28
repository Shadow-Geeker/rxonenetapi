package com.qbssh.lyd.rxonenetapi.communication;

import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DATA_STREAMS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DATA_STREAM_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DATA_STREAM_IDS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICES;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;

/**
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/17/2017.
 */
public interface DataStreamApi {

    /**
     * Create a new data stream.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id
     * @param json Data stream content json.
     * @return A {@link Observable} with generic type.
     * @deprecated Don't use this method, use data stream template.
     */
    @Deprecated
    @POST(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_STREAMS)
    Observable<OneNetResponse> addDataStream(@HeaderMap Map<String, String> headers,
                                             @Path(DEVICE_ID) String deviceId,
                                             @Body String json);

    /**
     * Update a data streams
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param deviceId Device id
     * @param dataStreamId Data stream id
     * @param json Data stream json
     * @return A {@link Observable} with generic type.
     */
    @PUT(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_STREAMS + "/{" + DATA_STREAM_ID + "}")
    Observable<OneNetResponse> updateDataStream(@HeaderMap Map<String, String> headers,
                                                @Path(DEVICE_ID) String deviceId,
                                                @Path(value = DATA_STREAM_ID, encoded = true) String dataStreamId,
                                                @Body String json);

    /**
     * Query a single device's data stream.
     * @param apiKey Master or device api key.
     * @param deviceId Device id
     * @param dataStreamId Data stream id
     * @return A {@link Observable} with generic type.
     */
    @GET(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_STREAMS + "/{" + DATA_STREAM_ID + "}")
    Observable<OneNetResponse> queryDataStream(@Header(API_KEY) String apiKey,
                                               @Path(DEVICE_ID) String deviceId,
                                               @Path(value = DATA_STREAM_ID, encoded = true) String dataStreamId);

    /**
     * Query amount of data streams.
     * @param apiKey Master or device api key.
     * @param deviceId Device id
     * @param dataStreamIds Data stream ids.
     * @return A {@link Observable} with generic type.
     */
    @GET(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_STREAMS)
    Observable<OneNetResponse> queryDatastreams(@Header(API_KEY) String apiKey,
                                                @Path(DEVICE_ID) String deviceId,
                                                @Query(value = DATA_STREAM_IDS, encoded = true) String dataStreamIds);

    /**
     * Delete a data stream
     * @param apiKey Master or device api key.
     * @param deviceId Device id
     * @param dataStreamId Data stream ids.
     * @return A {@link Observable} with generic type.
     */
    @DELETE(DEVICES + "/{" + DEVICE_ID + "}/" + DATA_STREAMS + "/{" + DATA_STREAM_ID + "}")
    Observable<OneNetResponse> deleteDatastream(@Header(API_KEY) String apiKey,
                                                @Path(DEVICE_ID) String deviceId,
                                                @Path(value = DATA_STREAM_ID, encoded = true) String dataStreamId);

}
