package com.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.config.TestConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.utils.GetSign.getTs;
import static com.utils.GetSign.makeSign;
import static com.utils.ParseJson.getJsonValue;

public class QueryUsers {
    //邮箱查找
    @Test(description = "邮箱查找人员",groups ={"login"} )
    public void queryUsersEmails() throws IOException {
        String queryType="1";
        JSONArray emailsArr=new JSONArray();
        String[] emails={"2017122@cadg.cn","jzzf@lltest.com","jz@lltest.com"};
        for(int i=0;i<emails.length;i++){
            emailsArr.add(emails[i]);
        }
        JSONArray userIdsArr=new JSONArray();
        String[] userIds={""};
        for(int i=0;i<userIds.length;i++){
            userIdsArr.add(userIds[i]);
        }
        JSONArray phoneArr=new JSONArray();
        String[] phones={""};
        for(int i=0;i<phones.length;i++){
            phoneArr.add(phones[i]);
        }
        String result;
        result=getResult(queryType,emailsArr,userIdsArr,phoneArr);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, "true");
    }
    @Test(description = "userId查找人员",groups ={"login"})
    public void queryUsersIds() throws IOException {
        String queryType="2";
        JSONArray emailsArr=new JSONArray();
        String[] emails={""};
        for(int i=0;i<emails.length;i++){
            emailsArr.add(emails[i]);
        }
        JSONArray userIdsArr=new JSONArray();
        String[] userIds={TestConfig.userId};
        for(int i=0;i<userIds.length;i++){
            userIdsArr.add(userIds[i]);
        }
        JSONArray phoneArr=new JSONArray();
        String[] phones={""};
        for(int i=0;i<phones.length;i++){
            phoneArr.add(phones[i]);
        }
        String result;
        result=getResult(queryType,emailsArr, userIdsArr, phoneArr);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, "true");
    }
    @Test(description = "phone查找人员",groups ={"login"} )
    public void queryUsersPhones() throws IOException {
        String queryType="3";
        JSONArray emailsArr=new JSONArray();
        String[] emails={""};
        for(int i=0;i<emails.length;i++){
            emailsArr.add(emails[i]);
        }
        JSONArray userIdsArr=new JSONArray();
        String[] userIds={""};
        for(int i=0;i<userIds.length;i++){
            userIdsArr.add(userIds[i]);
        }
        JSONArray phoneArr=new JSONArray();
        String[] phones={"17801036246","13263388629"};
        for(int i=0;i<phones.length;i++){
            phoneArr.add(phones[i]);
        }
        String result;
        result=getResult(queryType,emailsArr, userIdsArr, phoneArr);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, "true");

    }

   //发送请求
    private String getResult(String queryType, JSONArray emailsArr, JSONArray userIdsArr, JSONArray phoneArr) throws IOException {

        HttpPost post = new HttpPost(TestConfig.queryUsers);
        //json参数
        JSONObject param = new JSONObject();
        param.put("emails",emailsArr);
        param.put("userIds",userIdsArr);
        param.put("phones",phoneArr);
        param.put("entCode","litest");
        param.put("queryType",queryType);
        param.put("page","1");
        param.put("pageSize","50 ");
        //设置请求头信息
        post.setHeader("Content-type","application/json");
        post.setHeader("appid", "8cg78c041b2b28c734c3e5b7534cbb8p");
        String ts = getTs();
        post.setHeader("sign", makeSign("b774cfe9fc8776b961a650df3efb3mf3", "/external/api/user/queryUsers", ts));
        post.setHeader("ticket",TestConfig.ticket);
        //时间戳
        post.setHeader("ts", ts);
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //发送请求
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        return result;
    }
}
