package com.salmon.test.models.api;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Developer on 07/10/2016.
 */
public class ArrayAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings({"unchecked"})
    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {

        ArrayAdapter typeAdapter = null;
        try {
            if (type.getRawType() == List.class) {

                typeAdapter = new ArrayAdapter(
                        (Class) ((ParameterizedType) type.getType())
                                .getActualTypeArguments()[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return typeAdapter;
    }
}