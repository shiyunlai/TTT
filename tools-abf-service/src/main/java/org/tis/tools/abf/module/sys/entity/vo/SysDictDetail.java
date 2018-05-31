package org.tis.tools.abf.module.sys.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/5/31 14:48
 */
public class SysDictDetail implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private String guid = "";

    private String dictKey ="";

    private String dictName ="";

    private Boolean haveDitmItem = new Boolean(false);

    private List<Object> children = new ArrayList<Object>();

    public SysDictDetail(){}

    public SysDictDetail(String guid , String dictKey, String dictName,Boolean haveDitmItem,List<Object> children){
            this.dictKey = dictKey;
            this.dictName = dictName;
            this.haveDitmItem = haveDitmItem;
            this.children = children;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }


    public Boolean getHaveDitmItem() {
        return haveDitmItem;
    }

    public void setHaveDitmItem(Boolean haveDitmItem) {
        this.haveDitmItem = haveDitmItem;
    }
}
