package com.qbssh.lyd.rxonenetapi.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * OneNet Request Converter
 * @author LYD
 * @version 0.1
 *
 * @deprecated This is not used for convert your request to {@link RequestBody}. Please just only
 * use {@link OneNetResponseConverter}.
 */
@Deprecated
public class OneNetRequestConverter implements Converter<OneNetResponse, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private Gson gson;

    public OneNetRequestConverter(@NonNull Gson gson) {
        this.gson = gson;
    }

    @Override
    public RequestBody convert(OneNetResponse value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        OneNetResponse.typeAdapter(gson).write(jsonWriter, value);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}
