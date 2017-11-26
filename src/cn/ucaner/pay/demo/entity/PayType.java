package cn.ucaner.pay.demo.entity;

import cn.ucaner.pay.ali.api.AliPayConfigStorage;
import cn.ucaner.pay.ali.api.AliPayService;
import cn.ucaner.pay.ali.bean.AliTransactionType;
import cn.ucaner.pay.common.api.PayService;
import cn.ucaner.pay.common.bean.BasePayType;
import cn.ucaner.pay.common.bean.TransactionType;
import cn.ucaner.pay.union.fuyou.api.FuiouPayConfigStorage;
import cn.ucaner.pay.union.fuyou.api.FuiouPayService;
import cn.ucaner.pay.union.fuyou.bean.FuiouTransactionType;
import cn.ucaner.pay.wx.api.WxPayConfigStorage;
import cn.ucaner.pay.wx.api.WxPayService;
import cn.ucaner.pay.wx.bean.WxTransactionType;
import cn.ucaner.pay.wx.youdian.api.WxYouDianPayConfigStorage;
import cn.ucaner.pay.wx.youdian.api.WxYouDianPayService;
import cn.ucaner.pay.wx.youdian.bean.YoudianTransactionType;



/**
 * 支付类型
 *
 * @author egan
 * email egzosn@gmail.com
 * date 2016/11/20 0:30
 */
public enum PayType implements BasePayType {


    aliPay{
        /**
         *  @see cn.ucaner.pay.ali.api.AliPayService 17年更新的版本,旧版本请自行切换
         * @param apyAccount
         * @return
         */
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
            aliPayConfigStorage.setPid(apyAccount.getPartner());
            aliPayConfigStorage.setAppId(apyAccount.getAppid());
            aliPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            aliPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            aliPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            aliPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            aliPayConfigStorage.setSignType(apyAccount.getSignType());
            aliPayConfigStorage.setSeller(apyAccount.getSeller());
            aliPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            aliPayConfigStorage.setMsgType(apyAccount.getMsgType());
            aliPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            aliPayConfigStorage.setTest(apyAccount.isTest());
            return new AliPayService(aliPayConfigStorage);
        }

        @Override
        public TransactionType getTransactionType(String transactionType) {
            // cn.ucaner.pay.ali.before.bean.AliTransactionType 17年更新的版本,旧版本请自行切换

            // AliTransactionType 17年更新的版本,旧版本请自行切换
            return AliTransactionType.valueOf(transactionType);
        }


    },wxPay {
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
            wxPayConfigStorage.setMchId(apyAccount.getPartner());
            wxPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            wxPayConfigStorage.setAppid(apyAccount.getAppid());
            wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            wxPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            wxPayConfigStorage.setSignType(apyAccount.getSignType());
            wxPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            wxPayConfigStorage.setMsgType(apyAccount.getMsgType());
            wxPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            wxPayConfigStorage.setTest(apyAccount.isTest());
            return  new WxPayService(wxPayConfigStorage);
        }

        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see WxTransactionType
         * @return
         */
        @Override
        public TransactionType getTransactionType(String transactionType) {

            return WxTransactionType.valueOf(transactionType);
        }
    },youdianPay {
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            // TODO 2017/1/23 14:12 author: egan  集群的话,友店可能会有bug。暂未测试集群环境
            WxYouDianPayConfigStorage wxPayConfigStorage = new WxYouDianPayConfigStorage();
            wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            wxPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
//            wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
//            wxPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            wxPayConfigStorage.setSignType(apyAccount.getSignType());
            wxPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            wxPayConfigStorage.setMsgType(apyAccount.getMsgType());
            wxPayConfigStorage.setSeller(apyAccount.getSeller());
            wxPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            wxPayConfigStorage.setTest(apyAccount.isTest());
            return  new WxYouDianPayService(wxPayConfigStorage);
        }

        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see YoudianTransactionType
         * @return
         */
        @Override
        public TransactionType getTransactionType(String transactionType) {

            return YoudianTransactionType.valueOf(transactionType);
        }
    },fuiou{

        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            FuiouPayConfigStorage fuiouPayConfigStorage = new FuiouPayConfigStorage();
            fuiouPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            fuiouPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            fuiouPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            fuiouPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            fuiouPayConfigStorage.setSignType(apyAccount.getSignType());
            fuiouPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            fuiouPayConfigStorage.setMsgType(apyAccount.getMsgType());
            fuiouPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            fuiouPayConfigStorage.setTest(apyAccount.isTest());
            return new FuiouPayService(fuiouPayConfigStorage);
        }

        @Override
        public TransactionType getTransactionType(String transactionType) {
            return FuiouTransactionType.valueOf(transactionType);
        }


    };

    public abstract PayService getPayService(ApyAccount apyAccount);


}
