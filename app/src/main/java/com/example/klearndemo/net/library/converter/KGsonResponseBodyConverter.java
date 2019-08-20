package com.example.klearndemo.net.library.converter;

import com.example.klearndemo.base.BaseConfig;
import com.example.klearndemo.entity.BaseHttpResponse;
import com.example.klearndemo.net.exception.ApiException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Hpack;
import retrofit2.Converter;

import static java.nio.charset.StandardCharsets.UTF_8;

final class KGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    KGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = this.gson.newJsonReader(value.charStream());

        BaseHttpResponse<T> result;
        try {
            result = (BaseHttpResponse<T>) this.adapter.read(jsonReader);
            //code!=1,服务器校验返回的错误信息
            if (result != null && result.getCode() != BaseConfig.ApiConstants.CODE_RESPONSE_DATA_SUCCESS){
                throw new ApiException(result.getCode(),result.getResultMessage());
            }
        } finally {
            value.close();
        }
        //返回实际的Entity
        return result == null ? null : result.getResult();
    }
}
