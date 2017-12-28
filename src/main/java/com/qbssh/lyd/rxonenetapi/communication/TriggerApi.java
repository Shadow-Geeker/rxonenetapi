package com.qbssh.lyd.rxonenetapi.communication;


import com.qbssh.lyd.rxonenetapi.communication.UrlConditionBuilder.TriggerFuzzyQueryBuilder;
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
import retrofit2.http.QueryMap;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.TRIGGERS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.TRIGGER_ID;

/**
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/17/2017.
 *
 * @see <a href="https://open.iot.10086.cn/doc/art259.html#68" />
 */

public interface TriggerApi {

    /**
     * Add a new trigger.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param json Trigger content
     * @return A {@link Observable} object with generic type
     */
    @POST(TRIGGERS)
    Observable<OneNetResponse> addTrigger(@HeaderMap Map<String, String> headers,
                                          @Body String json);

    /**
     * Update a trigger.
     * @param headers HTTP headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to build.
     * @param triggerId Trigger id that will be updated
     * @param json Trigger content
     * @return A {@link Observable} object with generic type
     */
    @PUT(TRIGGERS + "/{" + TRIGGER_ID + "}")
    Observable<OneNetResponse> updateTrigger(@HeaderMap Map<String, String> headers,
                                             @Path(TRIGGER_ID) String triggerId,
                                             @Body String json);

    /**
     * Query a trigger.
     * @param apiKey Master api key
     * @param triggerId Trigger id
     * @return A {@link Observable} object with generic type
     */
    @GET(TRIGGERS + "/{" + TRIGGER_ID + "}")
    Observable<OneNetResponse> queryTrigger(@Header(API_KEY) String apiKey,
                                            @Path(TRIGGER_ID) String triggerId);

    /**
     * Fuzzy query triggers
     * @param apiKey Master api key
     * @param conditions Query conditions. Use {@link TriggerFuzzyQueryBuilder} to build a condition map.
     * @return A {@link Observable} object with generic type
     */
    // TODO : check if need encoded, because title need encoded
    @GET(TRIGGERS)
    Observable<OneNetResponse> fuzzyQueryTrigger(@Header(API_KEY) String apiKey,
                                                 @QueryMap(encoded = true) Map<String, String> conditions);

    /**
     * Delete a trigger.
     * @param apiKey Master api key
     * @param triggerId Trigger id
     * @return A {@link Observable} object with generic type
     */
    @DELETE(TRIGGERS + "/{" + TRIGGER_ID + "}")
    Observable<OneNetResponse> deleteTrigger(@Header(API_KEY) String apiKey,
                                             @Path(TRIGGER_ID) String triggerId);

}
