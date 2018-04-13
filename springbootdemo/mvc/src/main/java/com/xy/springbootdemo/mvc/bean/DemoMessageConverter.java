package com.xy.springbootdemo.mvc.bean;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * HttpMessageConverter是用来处理request和response里的数据的
 */
public class DemoMessageConverter extends AbstractHttpMessageConverter<DemoObj>{


    /**
     * 表明处理的媒体类型
     */
    public DemoMessageConverter() {
        super(new MediaType("application","x-wisely",Charset.forName("UTF-8")));
    }

    /**
     * 表明本HttpMessageConverter只处理DemoObj这个类
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 处理请求的数据，假设数据由"-"隔开，如"1-xiayun-27"，转换成DemoObj的对象
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        DemoObj obj = new DemoObj();
        obj.setId(new Integer(tempArr[0]));
        obj.setName(tempArr[1]);
        obj.setAge(new Integer(tempArr[2]));
        return obj;
    }

    /**
     * 处理返回的数据，假设在原样输出的前面加上“hello：”
     * @param demoObj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:"+demoObj.getId()+"-"+demoObj.getName()+"-"+demoObj.getAge();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
