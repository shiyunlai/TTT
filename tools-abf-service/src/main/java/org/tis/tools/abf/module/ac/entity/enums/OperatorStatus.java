package org.tis.tools.abf.module.ac.entity.enums;

import org.tis.tools.core.entity.enums.BaseEnum;

import java.io.Serializable;

/**
 * @author  wfl
 * @date 20180516
 */
public enum OperatorStatus implements BaseEnum {

    login("login","正常","正在登陆，处于活动状态"),

    pause("pause","挂起","暂时停止操作活动，处于锁屏状态"),

    clear("clear","注销","操作员身份已经注销，不能正常使用"),

    lock("lock","锁定","由于异常操作，操作员已经被锁定，解锁前不能正常使用系统功能"),

    logout("logout","退出","退出系统，当前处于非活动状态"),

    stop("stop","停用","新建操作员，还未启用");

    private final String value;

    private final String name;

    private final String desc;

    OperatorStatus(final String value,final String name,final String desc){
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
