package org.tis.tools.abf.module.common.entity.vo;

import lombok.Data;
import org.tis.tools.abf.module.common.entity.Tree;
import org.tis.tools.abf.module.common.entity.enums.YON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 为返回树结构接口的VO类
 * Created by chenchao
 * Created on 2018/6/14 14:23
 */
@Data
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

    public TreeDetail(String label,String data,String guid,String code,String expandedIcon,String collapsedIcon,YON isleaf, List<Tree> children){
        this.label = label;
        this.data = data;
        this.guid = guid;
        this.code = code;
        this.expandedIcon = expandedIcon;
        this.collapsedIcon = collapsedIcon;
        this.isleaf = isleaf;
        this.children = children;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
