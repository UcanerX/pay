package cn.ucaner.pay.demo.service.handler;


import java.util.Map;

import cn.ucaner.pay.common.api.PayService;
import cn.ucaner.pay.common.bean.PayMessage;
import cn.ucaner.pay.common.bean.PayOutMessage;
import cn.ucaner.pay.common.exception.PayErrorException;

/**
 * 微信支付回调处理器
 * Created by ZaoSheng on 2016/6/1.
 */
public class WxPayMessageHandler extends BasePayMessageHandler {



    public WxPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if ("SUCCESS".equals(payMessage.getPayMessage().get("result_code"))){
            /////这里进行成功的处理

            return  payService.getPayOutMessage("SUCCESS", "OK");
        }

        return  payService.getPayOutMessage("FAIL", "失败");
    }
}
