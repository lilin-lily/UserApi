package com.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lil on 2020/7/7.
 */
public class GetSign {
  /*  //测试用
    public static void main(String[] args){
        String sign=getSign("/external/api/user/request_ticket");
        System.out.println(sign);
    }

*/
    public static String getTs(){
        String ts;
        ts=String.valueOf(Calendar.getInstance().getTimeInMillis());
        return ts;
    }
    public static String makeSign(String appkey, String url, String timestamp) {
        String text = String.format("%s,%s,%s",appkey,url,timestamp);
        System.out.println(text);
        String sign = DigestUtils.sha1Hex(text);
        return sign;
    }
}
