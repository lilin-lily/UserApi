package com.model;

import lombok.Data;

/**
 * Created by lil on 2020/7/7.
 */
@Data
    public class User {
        private String userName;
        private String code;
        private String password;
        private String expected;
        private String id;

    public String getUserName() {
        return userName;
    }

    public String getCode() {
        return code;
    }

    public String getPassword() {
        return password;
    }

    public String getExpected() {
        return expected;
    }
    

       /* @Override
        public String toString(){
            return (
                    "id:"+id+","+
                            "userName:"+userName+","+
                            "password:"+password+","+
                            "code:"+code+","+
                            "expected:"+expected+"}"
            );
        }
*/
}