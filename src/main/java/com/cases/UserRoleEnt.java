package com.cases;

import com.alibaba.fastjson.JSONObject;
import com.config.TestConfig;
import com.model.InterfaceName;
import com.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.utils.GetSign.getTs;
import static com.utils.GetSign.makeSign;
import static com.utils.ParseJson.getJsonValue;

public class UserRoleEnt {
    @Test(description = "得到当前用户的角色信息",groups ={"login"} )
    public void getUserRoleEnt() throws IOException {
        HttpPost post = new HttpPost(TestConfig.userRole);
        JSONObject param = new JSONObject();
        param.put("userId", TestConfig.userId);
        //param.put("expected",UserModel.getExpected());
        //设置请求头信息
        post.setHeader("Content-type", "application/json");
        post.setHeader("appid", "8cg78c041b2b28c734c3e5b7534cbb8p");
        String ts = getTs();
        post.setHeader("sign", makeSign("b774cfe9fc8776b961a650df3efb3mf3", "/external/api/user/getUserRoleInEnt", ts));
        post.setHeader("ticket",TestConfig.ticket);
        //时间戳
        post.setHeader("ts", ts);
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, "true");
    }
}
