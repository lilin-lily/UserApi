package com.config;

import lombok.Data;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by lil on 2019/3/18.
 */
@Data
public class TestConfig {
    //获取项目进度模板
    public static String login;
    public static String userRole;
    public static String queryUsers;
    public static CookieStore store;
    public static String ticket;
    public static String userId;
    //声明http客户端
    public static DefaultHttpClient defaultHttpClient;
}
