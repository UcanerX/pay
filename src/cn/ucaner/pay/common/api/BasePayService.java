package cn.ucaner.pay.common.api;


import java.util.Map;

import cn.ucaner.pay.common.http.HttpConfigStorage;
import cn.ucaner.pay.common.http.HttpRequestTemplate;
import cn.ucaner.pay.common.util.sign.SignUtils;

/**
 * 支付基础服务
 * @author: egan
 *  <pre>
 *      email egzosn@gmail.com
 *      date 2017/3/5 20:36
 *   </pre>
 */
public abstract class BasePayService implements PayService {

    protected PayConfigStorage payConfigStorage;

    protected HttpRequestTemplate requestTemplate;
    protected int retrySleepMillis = 1000;

    protected int maxRetryTimes = 5;

    /**
     * 设置支付配置
     * @param payConfigStorage 支付配置
     */
    public BasePayService setPayConfigStorage(PayConfigStorage payConfigStorage) {
        this.payConfigStorage = payConfigStorage;
        return this;
    }

    @Override
    public PayConfigStorage getPayConfigStorage() {
        return payConfigStorage;
    }
    @Override
    public HttpRequestTemplate getHttpRequestTemplate() {
        return requestTemplate;
    }

    /**
     * 设置并创建请求模版， 代理请求配置这里是否合理？？，
     * @param configStorage http请求配置
     * @return 支付服务
     */
    @Override
    public BasePayService setRequestTemplateConfigStorage(HttpConfigStorage configStorage) {
        this.requestTemplate = new HttpRequestTemplate(configStorage);
        return this;
    }


    public BasePayService(PayConfigStorage payConfigStorage) {
        this(payConfigStorage, null);
    }

    public BasePayService(PayConfigStorage payConfigStorage, HttpConfigStorage configStorage) {
        setPayConfigStorage(payConfigStorage);
        setRequestTemplateConfigStorage(configStorage);
    }
    /**
     * 创建签名
     *
     * @param content           需要签名的内容
     * @param characterEncoding 字符编码
     * @return 签名
     */
    @Override
    public String createSign(String content, String characterEncoding) {

        return  SignUtils.valueOf(payConfigStorage.getSignType()).createSign(content, payConfigStorage.getKeyPrivate(),characterEncoding);
    }
    /**
     * 创建签名
     *
     * @param content           需要签名的内容
     * @param characterEncoding 字符编码
     * @return 签名
     */
    @Override
    public String createSign(Map<String, Object> content, String characterEncoding) {
        return  SignUtils.valueOf(payConfigStorage.getSignType()).sign(content, payConfigStorage.getKeyPrivate(),characterEncoding);
    }



}
