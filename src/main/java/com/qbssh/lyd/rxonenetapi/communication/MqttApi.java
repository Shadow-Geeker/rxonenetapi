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
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_TOPIC;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.MQTT;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.TOPIC;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.TOPIC_DEIVCE;

/**
 * Extended api for MQTT device.
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/27/2017.
 */

public interface MqttApi {

    /**
     * Send command by topic for MQTT devices.
     * @param headers Contains device api key or master api key. Use {@link UrlConditionBuilder.HeadersMapBuilder}
     *                to build a header map.
     * @param topic Topic
     * @param body Request body
     * @return An {@link Observable} with {@link OneNetResponse} type
     */
    @POST(MQTT)
    Observable<OneNetResponse> sendCmdByTopic(@HeaderMap Map<String, String> headers,
                                              @Query(value = TOPIC, encoded = true) String topic,
                                              @Body String body);

    /**
     * Query devices by topic
     * @param apiKey Master api key
     * @param topic Topic
     * @param page Page count for result
     * @param perPage Amount of result per page
     * @return An {@link Observable} with {@link OneNetResponse} type
     */
    @GET(MQTT + "/" + TOPIC_DEIVCE)
    Observable<OneNetResponse> queryDevicesByTopic(@Header(API_KEY) String apiKey,
                                                   @Query(value = TOPIC, encoded = true) String topic,
                                                   @Query("page") int page,
                                                   @Query("per_page") int perPage);


    /**
     * Query device topics.
     * @param headers Contains device api key or master api key. Use {@link UrlConditionBuilder.HeadersMapBuilder}
     *                to build a header map.
     * @param deviceId Device id.
     * @return An {@link Observable} with {@link OneNetResponse} type
     */
    @GET(MQTT + "/" + DEVICE_TOPIC + "/{" + DEVICE_ID + "}")
    Observable<OneNetResponse> queryDeviceTopics(@HeaderMap Map<String, String> headers,
                                                 @Path(DEVICE_ID) String deviceId);

    /**
     * Add a new topic.
     * @param headers Contains device api key or master api key. Use {@link UrlConditionBuilder.HeadersMapBuilder}
     *                to build a header map.
     * @param body Topic body
     * @return An {@link Observable} with {@link OneNetResponse} type
     */
    @POST(MQTT + "/" + TOPIC)
    Observable<OneNetResponse> addTopic(@HeaderMap Map<String, String> headers,
                                        @Body String body);

    /**
     * Delete a topic.
     * @param apiKey Master api key
     * @param topic Topic name
     * @return An {@link Observable} with {@link OneNetResponse} type
     */
    @DELETE(MQTT + "/" + TOPIC)
    Observable<OneNetResponse> deleteTopic(@Header(API_KEY) String apiKey,
                                           @Query(value = "name", encoded = true) String topic);

    /**
     * Query all topics.
     * @param apiKey Master api key
     * @return An {@link Observable} with {@link OneNetResponse} type
     */
    @GET(MQTT + "/" + TOPIC)
    Observable<OneNetResponse> queryTopics(@Header(API_KEY) String apiKey);


}
