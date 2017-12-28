package com.qbssh.lyd.rxonenetapi.communication;


import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;
import com.qbssh.lyd.rxonenetapi.communication.UrlConditionBuilder.ApiKeyQueryBuilder;

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
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.CONTENT_TYPE;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.KEYS;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.KEY_STRING;

/**
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/17/2017.
 *
 * @see <a href="https://open.iot.10086.cn/doc/art296.html#68" />
 */

public interface ApiKeyApi {

    /**
     * Add a new api key.
     * @param map Header map to build headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to
     *            build a header map
     * @param json Api key content
     * @return A {@link Observable} with generic type
     */
    @POST(KEYS)
    Observable<OneNetResponse> addApiKey(@HeaderMap Map<String, String> map,
                                         @Body String json);

    /**
     * Update an api key.
     * @param map Header map to build headers, use {@link UrlConditionBuilder.HeadersMapBuilder} to
     *            build a header map
     * @param keyString The key
     * @param body Updated api key content
     * @return A {@link Observable} with generic type
     */
    @PUT(KEYS + "/{" + KEY_STRING + "}")
    Observable<OneNetResponse> updateApiKey(@HeaderMap Map<String, String> map,
                                            @Path(KEY_STRING) String keyString,
                                            @Body String body);

    /**
     * Query api keys.
     * @param apiKey Only can be master api key
     * @param map Condition map. Use {@link ApiKeyQueryBuilder} to build a condition map
     * @return A {@link Observable} with generic type
     */
    @GET(KEYS)
    Observable<OneNetResponse> queryApiKeys(@Header(API_KEY) String apiKey,
                                            @QueryMap Map<String, String> map);

    /**
     * Delete an api key.
     * @param apiKey Only can be master api key
     * @param keyString The key
     * @return  A {@link Observable} with generic type
     */
    @DELETE(KEYS + "/{" + KEY_STRING + "}")
    Observable<OneNetResponse> deleteApiKey(@Header(API_KEY) String apiKey,
                                            @Path(KEY_STRING) String keyString);
}
