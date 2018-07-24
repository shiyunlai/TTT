package org.tis.tools.abf.module.common.entity;

import lombok.Data;
import org.tis.tools.abf.module.common.entity.enums.YON;

/**
 * 返回树结构的实体类
 * Created by chenchao
 * Created on 2018/6/14 14:42
 */
@Data
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

    @Override
    public String toString() {
        return super.toString();
    }
}
