/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.kevin.ksoup.sample.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * StringConvertFactory
 *
 * @author zhouwenkai@baidu.com, Created on 2018-01-29 18:58:46
 *         Major Function：<b>Retrofit String Convertor</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public class StringConvertFactory extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    private StringConvertFactory() {
    }

    public static StringConvertFactory create() {
        return new StringConvertFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    String content = value.string();
                    value.close();
                    return content;
                }
            };
        }
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value);
                }
            };
        }
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }
}
