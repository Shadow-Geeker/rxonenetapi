package com.qbssh.lyd.rxonenetapi.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * OneNet response converter.
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/20/2017.
 */

public class OneNetResponseConverterFactory extends Converter.Factory {

    public static OneNetResponseConverterFactory create() { return create(new Gson()); }

    public static OneNetResponseConverterFactory create(final Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new OneNetResponseConverterFactory(gson);
    }

    private Gson gson;

    private OneNetResponseConverterFactory(@NonNull Gson gson) {
        this.gson = gson;
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (OneNetResponse.class.equals(type)) {
            return new OneNetResponseConverter(gson);
        }
        return null;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (OneNetResponse.class.equals(type)) {
            return new OneNetRequestConverter(gson);
        }
        return null;
    }
}
