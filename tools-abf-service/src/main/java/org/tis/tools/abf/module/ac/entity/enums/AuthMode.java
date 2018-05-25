package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

public enum AuthMode implements BaseEnum {

    password("password","密码","静态密码"),

    dynpassword("dynpassword","动态密码","一般时系统发送实时登陆密码到手机"),

    captcha("captcha","验证码","系统将验证码发送给操作员"),

    ldap("ldap","LDAP认证",""),

    fingerprint("fingerprint","指纹",""),

    fingerprintcard("fingerprintcard","指纹卡","供操作员无法正常输入指纹时使用")

    ;
    private final String value;

    private final String name;

    private  final String desc;


    AuthMode( final String value,final String name,final String desc){
        this.value = value;
        this.name = name;
        this.desc = desc;

    }
    @Override
    public String toString() {
        return name;
    }

    @Override
    public Serializable deserialze() {
        return value;
    }

    @Override
    public Serializable getValue() {
        return value;
    }
}
