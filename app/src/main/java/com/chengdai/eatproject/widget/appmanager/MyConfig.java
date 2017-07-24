package com.chengdai.eatproject.widget.appmanager;

/**
 * Created by 李先俊 on 2017/6/9.
 */

public class MyConfig {

    public final static String COMPANYCODE="CD-CYC000009";
    public final static String SYSTEMCODE="CD-CYC000009";

    public final static String IMGURL="http://oq4vi26fi.bkt.clouddn.com/";
    // 无损压缩
    public final static String IMGURLCOMPRESS = "?imageslim";
    /*http://devyc.m.hijuniu.com?userRefereeKind=taster&userReferee=15268501481*/

    /*1待支付 2 已支付待发货 3 已发货待收货 4 已收货 91用户取消 92 商户取消 93 快递异常*/
    public final static String ORDERTYPEWAITPAY="1";
    public final static String ORDERTYPEWAITFAHUO="2";
    public final static String ORDERTYPEWAITSHOUHUO="3";
    public final static String ORDERTYPEYISHOUHUO="4";
    public final static String ORDERTYPECANCELUSER="91";
    public final static String ORDERTYPECANCELSHOP="92";
    public final static String ORDERTYPEERROR="93";


}
