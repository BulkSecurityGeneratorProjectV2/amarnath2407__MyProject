package com.salmon.test.models.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 07/10/2016.
 */
public class ArrayAdapter<T> extends TypeAdapter<List<T>> {
    private Class<T> adapterclass;

    public ArrayAdapter(Class<T> adapterclass) {

        this.adapterclass = adapterclass;
    }

    public List<T> read(JsonReader reader) throws IOException {


        List<T> list = new ArrayList<T>();

        Gson gson = new Gson();

        if (reader.peek() == JsonToken.STRING) {

            T inning = (T) gson.fromJson(reader, adapterclass);
            list.add(inning);

        } else if (reader.peek() == JsonToken.BEGIN_ARRAY) {

            reader.beginArray();
            while (reader.hasNext()) {
                if(adapterclass instanceof Class) {
                    gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
                }
                T inning = (T) gson.fromJson(reader, adapterclass);
                list.add(inning);
            }
            reader.endArray();

        } else {
            reader.skipValue();
        }

        return list;
    }

    public void write(JsonWriter writer, List<T> value) throws IOException {

    }

}
