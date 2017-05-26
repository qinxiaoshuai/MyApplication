package com.example.mydream.mydreamapp.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 项目名称：MyApplication
 * 创建人：My Dream
 * 创建时间：2017/5/8 16:46
 */
public class MydraemAppUser extends BmobUser{

    private String uname;
    private String upassword;
    private BmobFile upicture;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }



}
