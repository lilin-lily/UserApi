package com.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class ParseJson {
    //取json中的值
    public static String getJsonValue(String result, String JsonId){
        String JsonValue = "";
        if (result == null || result.trim().length() < 1) {
            return null;
        }
        try {
            //转换成object对象
            JSONObject jsonObject = JSONObject.parseObject(result);
            //解析某个key对应的value
            JsonValue = jsonObject.getString(JsonId);
            //String name = obj.getObject("data").getString("ticket");{name:aa,status{key:value}
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return JsonValue;
    }
    //取jsondata中的值
    public static String getJsonDataValue(String result, String data, String ticket) {
        String JsonValue = "";
        if (result == null || result.trim().length() < 1) {
            return null;
        }
        try {
            //转换成object对象
            JSONObject jsonObject = JSONObject.parseObject(result);
            //解析某个key对应的value
            JsonValue = jsonObject.getString(data);
            //String name = obj.getObject("data").getString("ticket");{name:aa,status{key:value}
            JSONObject jsonObject1=JSONObject.parseObject(JsonValue);
            ticket=jsonObject1.getString(ticket);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ticket;
    }

}
