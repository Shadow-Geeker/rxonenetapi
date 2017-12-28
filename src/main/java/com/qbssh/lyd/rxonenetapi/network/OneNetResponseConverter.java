package com.qbssh.lyd.rxonenetapi.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 *
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/20/2017.
 */

public class OneNetResponseConverter implements Converter<ResponseBody, OneNetResponse> {

    private Gson gson;

    public OneNetResponseConverter(@NonNull Gson gson) {
        this.gson = gson;
    }

    @Override
    public OneNetResponse convert(ResponseBody value) throws IOException {
        return OneNetResponse.typeAdapter(gson).fromJson(value.charStream());
    }
}
