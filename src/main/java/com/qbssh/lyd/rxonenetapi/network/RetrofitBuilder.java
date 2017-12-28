package com.qbssh.lyd.rxonenetapi.network;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.qbssh.lyd.rxonenetapi.network.OneNetUrl.DEFAULT;

/**
 *
 * @author LYD
 * @version 0.1
 *
 * Created by LYD on 12/20/2017.
 */

public class RetrofitBuilder {

    private List<TypeAdapterFactory> typeAdapterFactories = new ArrayList<>();
    private List<Interceptor> interceptors = new ArrayList<>(), networkInterceptors = new ArrayList<>();

    private String baseUrl;

    /**
     * Set OneNet api base url.
     * @param url OneNet api base url. Default is {@link OneNetUrl#DEFAULT}. The url is based on
     *            province because some provinces in China using their own url address. Usually,
     *            the api url address is like "http://api.xx.cmcconenet.com/", xx is the province's
     *            phonetic abbreviation, such as the api in Guangdong is "http://api.gd.cmcconenet.com/".
     *            I try all the provinces and cities, including HongKong, AoMen and Taiwan. And all
     *            the result are in {@link OneNetUrl}. But I'm not sure that there are some url address
     *            I might lose. If you find something, let me know. My email is yadong.li@qbs-sh.com.
     * @return RetrofitBuilder self
     *
     * @see OneNetUrl
     */
    public RetrofitBuilder baseUrl(String url) {
        this.baseUrl = url;
        return this;
    }

    /**
     * Set additional type adapter factory.
     * @param factory self defined or other {@link TypeAdapterFactory} object.
     * @return RetrofitBuilder self
     */
    public RetrofitBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
        if (factory != null) {
            typeAdapterFactories.add(factory);
        }
        return this;
    }

    public RetrofitBuilder registerTypeAdapterFactories(List<TypeAdapterFactory> factories) {
        if (factories != null && factories.size() > 0) {
            typeAdapterFactories.addAll(factories);
        }
        return this;
    }

    public RetrofitBuilder registerTypeAdapterFactories(TypeAdapterFactory... factories) {
        if (factories != null && factories.length > 0) {
            typeAdapterFactories.addAll(Arrays.asList(factories));
        }
        return this;
    }

    public RetrofitBuilder addInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            interceptors.add(interceptor);
        }
        return this;
    }

    public RetrofitBuilder addInterceptors(List<Interceptor> inters) {
        if (inters != null && inters.size() > 0) {
            interceptors.addAll(inters);
        }
        return this;
    }

    public RetrofitBuilder addInterceptors(Interceptor... inters) {
        if (inters != null && inters.length > 0) {
            interceptors.addAll(Arrays.asList(inters));
        }
        return this;
    }

    public RetrofitBuilder addNetworkInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            networkInterceptors.add(interceptor);
        }
        return this;
    }

    public RetrofitBuilder addNetworkInterceptors(List<Interceptor> inters) {
        if (inters != null && inters.size() > 0) {
            networkInterceptors.addAll(inters);
        }
        return this;
    }

    public RetrofitBuilder addNetworkInterceptors(Interceptor... inters) {
        if (inters != null && inters.length > 0) {
            networkInterceptors.addAll(Arrays.asList(inters));
        }
        return this;
    }

    public Retrofit build() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapterFactory(OneNetResponseGsonAdapter.create());
        if (typeAdapterFactories.size() > 0) {
            for (TypeAdapterFactory factory : typeAdapterFactories) {
                builder.registerTypeAdapterFactory(factory);
            }
        }
        GsonConverterFactory factory = GsonConverterFactory.create(builder.create());
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        if (interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                okBuilder.addInterceptor(interceptor);
            }
        }
        if (networkInterceptors.size() > 0) {
            for (Interceptor interceptor : networkInterceptors) {
                okBuilder.addNetworkInterceptor(interceptor);
            }
        }
        OkHttpClient client = okBuilder.build();
        return new Retrofit.Builder().client(client)
                .baseUrl(baseUrl == null ? DEFAULT : baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
