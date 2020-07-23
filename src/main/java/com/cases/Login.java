package com.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.config.TestConfig;
import com.model.InterfaceName;
import com.model.User;
import com.utils.ConfigFile;
import com.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.utils.GetSign.*;
import static com.utils.ParseJson.getJsonDataValue;
import static com.utils.ParseJson.getJsonValue;


/**
 * Created by lil on 2020/7/7.
 */
public class Login {
    @BeforeTest
    public void beforeTest() {
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        //协同模块
        TestConfig.login = ConfigFile.getHost(InterfaceName.USERLOGIN);
        TestConfig.userRole = ConfigFile.getHost(InterfaceName.USERROLEENT);
        TestConfig.queryUsers=ConfigFile.getHost(InterfaceName.QUERYUSERS);
    }

    //需要连接数据库
    @Test(description = "正常登录")
    public void loginTrue() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        //获取表中的第四条数据
        User UserModel = session.selectOne("UserModel", 1);
        System.out.println(UserModel.toString());
        //存放返回结果
        String result;
        //发请求，取结果
        result = getResult(UserModel);
        System.out.println(result);
        Thread.sleep(2000);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, UserModel.getExpected());
        //String ticket=null;
        TestConfig.ticket = getJsonDataValue(result, "data", "ticket");
        TestConfig.userId = getJsonDataValue(result, "data", "userId");
    }

    @Test(description = "手机号登录",enabled = true)
    public void loginPho() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        //获取表中的第2条数据
        User UserModel = session.selectOne("UserModel", 2);
        System.out.println(UserModel.toString());
        //存放返回结果
        String result;
        //发请求，取结果
        result = getResult(UserModel);
        System.out.println(result);
        Thread.sleep(2000);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, UserModel.getExpected());
    }

    @Test(description = "停用用户登录",enabled = true)
    public void loginFalse() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        //获取表中的第3条数据
        User UserModel = session.selectOne("UserModel", 3);
        System.out.println(UserModel.toString());
        //存放返回结果
        String result;
        //发请求，取结果
        result = getResult(UserModel);
        System.out.println(result);
        Thread.sleep(2000);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, UserModel.getExpected());
    }

    @Test(description = "登录失败-password",enabled = true)
    public void loginFalsepassword() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        //获取表中的第四条数据
        User UserModel = session.selectOne("UserModel", 4);
        System.out.println(UserModel.toString());
        //存放返回结果
        String result;
        //发请求，取结果
        result = getResult(UserModel);
        System.out.println(result);
        Thread.sleep(2000);
        String success = null;
        success = getJsonValue(result, "success");
        //实际值与期望值进行断言
        Assert.assertEquals(success, UserModel.getExpected());
    }
        private String getResult(User UserModel) throws IOException {
            HttpPost post = new HttpPost(TestConfig.login);
            JSONObject param = new JSONObject();
            param.put("userName", UserModel.getUserName());
            param.put("code", UserModel.getCode());
            param.put("password", UserModel.getPassword());
            param.put("service", " ");
            //param.put("expected",UserModel.getExpected());
            //设置请求头信息
            post.setHeader("Content-type", "application/json");
            post.setHeader("appid", "8cg78c041b2b28c734c3e5b7534cbb8p");
            String ts = getTs();
            post.setHeader("sign", makeSign("b774cfe9fc8776b961a650df3efb3mf3", "/external/api/user/request_ticket", ts));
            //时间戳
            post.setHeader("ts", ts);
            StringEntity entity = new StringEntity(param.toString(), "utf-8");
            post.setEntity(entity);
            HttpResponse response = TestConfig.defaultHttpClient.execute(post);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            return result;
        }
    }

