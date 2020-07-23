/**
 * Created by lil on 2019/3/18.
 */
package com.utils;

import com.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    //获取配置文件
    private static ResourceBundle bundle=ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getHost(InterfaceName name){
        String host = bundle.getString("test.host");
        String uri = "";
        String testUrl;
        if(name==InterfaceName.USERLOGIN){
            uri=bundle.getString("user.login.uri");
        }
        if(name==InterfaceName.USERROLEENT){
            uri=bundle.getString("user.getUserRoleInEnt");
        }
        if(name==InterfaceName.QUERYUSERS){
            uri=bundle.getString("user.getQueryUsers");
        }
        testUrl = host + uri;
        return testUrl;
    }
}
