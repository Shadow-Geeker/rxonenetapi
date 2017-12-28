package com.qbssh.lyd.rxonenetapi.communication;


import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.API_KEY;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.BIN_DATA;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DATA_STREAM_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.DEVICE_ID;
import static com.qbssh.lyd.rxonenetapi.communication.UrlAddressPart.INDEX;


/**
 * Binary data api.
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/17/2017.
 */

public interface BinaryDataApi {

    /**
     * Add a new binary data.
     * @param deviceApiKey Device api key
     * @param device_id Device id
     * @param dataStreamId Data stream id
     * @param part {@link MultipartBody.Part} object to write. Use {@link UrlConditionBuilder.MultipartBodyBuilder}
     *              to build {@link MultipartBody.Part} from a {@link java.io.File} or byte array or a string.
     * @return A {@link Observable} object with generic type
     */
    @Multipart
    @POST(BIN_DATA)
    Observable<OneNetResponse> addBinaryData(@Header(API_KEY) String deviceApiKey,
                                             @Query(DEVICE_ID) String device_id,
                                             @Query(value = DATA_STREAM_ID, encoded = true) String dataStreamId,
                                             @Part MultipartBody.Part part);

    /**
     * Query binary data.
     * @param deviceApiKey Device api key
     * @param index Binary data start index
     * @return A {@link Observable} object with generic type
     */
    @GET(BIN_DATA + "/{" + INDEX + "}")
    Observable<OneNetResponse> queryBinaryData(@Header(API_KEY) String deviceApiKey,
                                               @Path(value = INDEX, encoded = true) String index);
}
