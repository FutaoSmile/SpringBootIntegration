package com.futao.springmvcdemo.foundation.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.futao.springmvcdemo.utils.TimeUtilsKt;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @author futao
 * Created on 2018-12-20.
 * <p>
 * 自定义HttpMessageConverter
 */
@Service
public class HttpMessageConverterConfiguration extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {
    public static final SerializerFeature[] SERIALIZER_FEATURES = new SerializerFeature[]{
            SerializerFeature.PrettyFormat
            , SerializerFeature.SkipTransientField
            , SerializerFeature.WriteEnumUsingName
            , SerializerFeature.WriteDateUseDateFormat
            , SerializerFeature.WriteNullStringAsEmpty
            , SerializerFeature.WriteNullListAsEmpty
            , SerializerFeature.WriteMapNullValue
    };

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return false;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        super.read(contextClass, inputMessage);
        return null;
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public void write(Object o, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        JSON.DEFFAULT_DATE_FORMAT = TimeUtilsKt.yyyyMMddHHmmss;
        outputMessage.getBody().write(JSON.toJSONString(o, SERIALIZER_FEATURES).getBytes(StandardCharsets.UTF_8));
    }
}
