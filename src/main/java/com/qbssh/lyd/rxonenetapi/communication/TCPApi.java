package com.qbssh.lyd.rxonenetapi.communication;


import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DTU;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.PARSER;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.PARSER_ID;


/**
 * TCP connection api.
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/18/2017.
 *
 * @see <a href="https://open.iot.10086.cn/doc/art298.html#68" />
 */

public interface TCPApi {

    /**
     * Add new script
     * @param apiKey Only can be master api key
     * @return A {@link Observable} object with generic type
     */
    @FormUrlEncoded
    @POST(DTU + "/" + PARSER)
    Observable<OneNetResponse> addScript(@Header(API_KEY) String apiKey);

    /**
     *
     * @param apiKey Master api key
     * @param file Uploaded file. Use {@link UrlConditionBuilder.MultipartBodyBuilder} to build a
     *             {@link MultipartBody.Part} from a file.
     * @return A {@link Observable} object with generic type
     */
    @Multipart
    @POST(DTU + "/" + PARSER)
    Observable<OneNetResponse> uploadScript(@Header(API_KEY) String apiKey,
                                            @Part MultipartBody.Part file);


    /**
     *
     * @param apiKey Master or device api key
     * @param parserId Parser id
     * @param json Update content
     * @return A {@link Observable} object with generic type
     */
    @PUT(DTU + "/" + PARSER + "/{" + PARSER_ID + "}")
    Observable<OneNetResponse> update(@Header(API_KEY) String apiKey,
                                      @Path(PARSER_ID) String parserId,
                                      @Body String json);

    /**
     * Query parsers with name filter.
     * @param apiKey Master or device api key
     * @param name Query name. Use {@link UrlConditionBuilder.TCPQueryBuilder} to build a query string
     * @return A {@link Observable} object with generic type
     */
    @POST(DTU + "/" + PARSER)
    Observable<OneNetResponse> query(@Header(API_KEY) String apiKey,
                                     @Query("name") String name);

    /**
     * Delete a parser.
     * @param apiKey Master or device api key
     * @param parserId Parser id
     * @return A {@link Observable} object with generic type
     */
    @DELETE(DTU + "/" + PARSER + "/{" + PARSER_ID + "}")
    Observable<OneNetResponse> delete(@Header(API_KEY) String apiKey,
                                      @Path(PARSER_ID) String parserId);

}
