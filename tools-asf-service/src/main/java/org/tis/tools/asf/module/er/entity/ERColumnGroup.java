package org.tis.tools.asf.module.er.entity;

import lombok.Data;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/5/17
 **/
@Data
public class ERColumnGroup {

    private String id;

    private String groupName;

    private ERColumns columns;

}
