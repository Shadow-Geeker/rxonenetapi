package com.qbssh.lyd.rxonenetapi.module;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * OneNet response body
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/15/2017.
 */

@AutoValue
public abstract class OneNetResponse {

    /**
     * OneNet response body element "errno"
     * @return the amount of errors. If it is zero, that means no error, otherwise
     * there are some errors such as device isn't online or other. Note these errors
     *  are not HTTP Exception because the communication is successful.
     */
    @SerializedName("errno") public abstract int errno();

    /**
     * The description of errors which is returned by OneNet. Can be null if the response
     * json contains no error field.
     * @return String type object that contains errors' description. Can be null.
     */
    @Nullable @SerializedName("error") public abstract String error();

    /**
     * OneNet response body data element which contains all the important info that you really want
     * to use or know.
     *
     * @return {@link JsonElement} object for using.
     */
    @Nullable @SerializedName("data") public abstract JsonElement data();

    /**
     * OneNetResponse creator
     * @param errno error number
     * @param error error description
     * @param data {@link JsonElement} data
     * @return {@link OneNetResponse} object
     */
    public static OneNetResponse create(int errno, @Nullable String error, @Nullable JsonElement data) {
        return new AutoValue_OneNetResponse(errno, error, data);
    }

    /**
     * Typed adapter for Google {@link Gson} to covert json to {@link OneNetResponse} object.
     * @param gson {@link Gson}
     * @return {@link TypeAdapter}<{@link OneNetResponse}> object for read and write json.
     */
    public static TypeAdapter<OneNetResponse> typeAdapter(Gson gson) {
        return new AutoValue_OneNetResponse.GsonTypeAdapter(gson);
    }
}
