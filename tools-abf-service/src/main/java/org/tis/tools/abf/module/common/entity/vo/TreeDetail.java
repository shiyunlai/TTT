package org.tis.tools.abf.module.common.entity.vo;

import org.tis.tools.abf.module.common.entity.Tree;
import org.tis.tools.abf.module.common.entity.enums.YON;
import org.tis.tools.abf.module.om.entity.enums.OmGroupStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 为返回树结构接口的VO类
 * Created by chenchao
 * Created on 2018/6/14 14:23
 */
public class TreeDetail implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

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

    private List<Tree> children = new ArrayList<Tree>();

    public TreeDetail(){}

    public TreeDetail(String label,String data,String guid,String code,String expandedIcon,String collapsedIcon,YON isleaf,OmGroupStatus status, List<Tree> children){
        this.label = label;
        this.data = data;
        this.guid = guid;
        this.code = code;
        this.expandedIcon = expandedIcon;
        this.collapsedIcon = collapsedIcon;
        this.isleaf = isleaf;
        this.children = children;
    }

    //get和set方法
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

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
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
