package org.tis.tools.abf.module.jnl.entity.vo;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.tis.tools.abf.module.jnl.entity.LogAbfChange;
import org.tis.tools.abf.module.jnl.entity.LogAbfData;
import org.tis.tools.abf.module.jnl.entity.enums.DataOperateType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LogDataDetail implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private LogAbfData obj = new LogAbfData();

    private List<LogAbfChange> changes = new ArrayList<>();

    /**
     * 添加变化项
     * @param physicalName
     * @param logicName
     * @param oldValue
     * @param newValue
     * @return
     */
    public LogDataDetail addChangeItem(String physicalName, String logicName, String oldValue, String newValue) {
        LogAbfChange logAbfChange = new LogAbfChange();
        logAbfChange.setPhysicalName(StringUtils.isBlank(physicalName) ? null : physicalName.trim());
        logAbfChange.setLogicName(StringUtils.isBlank(logicName) ? null : logicName.trim());
        logAbfChange.setOldValue(StringUtils.isBlank(oldValue) ? null : oldValue.trim());
        logAbfChange.setNewValue(StringUtils.isBlank(newValue) ? null : newValue.trim());
        this.changes.add(logAbfChange);
        return this;
    }

    /**
     * 添加变化项
     * @param changeItem
     * @return
     */
    public LogDataDetail addChangeItem(LogAbfChange changeItem) {
        this.changes.add(changeItem);
        return this;
    }

    public LogDataDetail setChanges(List<LogAbfChange> list) {
        if(!CollectionUtils.isEmpty(list)) {
            this.changes.addAll(list);
        }
        return this;
    }

    public void setObj(LogAbfData obj) {
        this.obj = obj;
    }

    public LogAbfData getInstance() {
        return obj;
    }

    public List<LogAbfChange> getChanges() {
        return changes;
    }


    public LogDataDetail setOperateType(DataOperateType operateType) {
        this.obj.setOperateType(operateType);
        return this;
    }

    /**
     * Set the 对象类名.
     *
     * @param dataClass 对象类名
     */
    public LogDataDetail setDataClass(String dataClass) {
        this.obj.setDataClass(dataClass == null ? null : dataClass.trim());
        return this;
    }


    /**
     * Set the 对象GUID.
     *
     * @param dataGuid 数据GUID
     */
    public LogDataDetail setDataGuid(String dataGuid) {
        this.obj.setDataGuid(dataGuid == null ? null : dataGuid.trim());
        return this;
    }


    /**
     * Set the 对象名.
     *
     * @param dataName 数据名
     */
    public LogDataDetail setDataName(String dataName) {
        this.obj.setDataName(dataName == null ? null : dataName.trim());
        return this;
    }


    /**
     * Set the 对象值.
     *
     * @param dataString 对象值
     */
    public LogDataDetail setDataString(String dataString) {
        this.obj.setDataString(dataString == null ? null : dataString.trim());
        return this;
    }


}
