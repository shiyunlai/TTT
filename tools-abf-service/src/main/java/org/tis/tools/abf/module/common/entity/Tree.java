package org.tis.tools.abf.module.common.entity;

import org.tis.tools.abf.module.common.entity.enums.YON;

/**
 * 返回树结构的实体类
 * Created by chenchao
 * Created on 2018/6/14 14:42
 */
public class Tree {

    //对象的名称
    private String label;
    //传输前端需要的数据
    private String data;
    //GUID
    private String guid;
    //CODE
    private String code;
    //展开的图标
    private String expandedIcon;
    //闭合的图标
    private String collapsedIcon;
    //是否叶子节点
    private YON isleaf;

    public Tree(){}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getExpandedIcon() {
        return expandedIcon;
    }

    public void setExpandedIcon(String expandedIcon) {
        this.expandedIcon = expandedIcon;
    }

    public String getCollapsedIcon() {
        return collapsedIcon;
    }

    public void setCollapsedIcon(String collapsedIcon) {
        this.collapsedIcon = collapsedIcon;
    }

    public YON getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(YON isleaf) {
        this.isleaf = isleaf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
