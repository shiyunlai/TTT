package org.tis.tools.model.log;

import lombok.Data;
import org.tis.tools.model.log.enums.OperateType;

import java.util.List;

/**
 * description: 日志数据对象
 *
 * @author zhaoch
 * @date 2018/5/15
 **/
@Data
public class LogData {

    /**
     * 数据操作类型
     */
    public OperateType operateType;

    /**
     * 数据GUID
     */
    public String dataGuid;

    /**
     * 数据名称
     */
    public String dataName;

    /**
     * 数据类名
     */
    public String dataClass;

    /**
     * 数据STRING值
     */
    public String dataString;

    /**
     * 改变项集合
     */
    public List<LogDataChange> changes;
}
