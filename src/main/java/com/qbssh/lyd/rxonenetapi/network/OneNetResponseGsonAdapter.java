package com.qbssh.lyd.rxonenetapi.network;

import com.google.gson.TypeAdapterFactory;
import com.qbssh.lyd.rxonenetapi.module.OneNetResponse;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Type adapter for {@link OneNetResponse}.
 * @author LYD
 * @version 0.1
 * Created by LYD on 12/20/2017.
 *
 * @see <a href="https://github.com/rharter/auto-value-gson#factory" />
 */
@GsonTypeAdapterFactory
public abstract class OneNetResponseGsonAdapter implements TypeAdapterFactory {

    public static OneNetResponseGsonAdapter create() {
        return new AutoValueGson_OneNetResponseGsonAdapter();
    }

}
