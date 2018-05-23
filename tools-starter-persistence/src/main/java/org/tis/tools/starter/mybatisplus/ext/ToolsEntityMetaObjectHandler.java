package org.tis.tools.starter.mybatisplus.ext;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * <pre>
 * 填充Tools实体固定字段
 * 固定字段包括：创建时间、创建人、上次更新时间、更新人
 * </pre>
 *
 * @author Shiyunlai
 * @since 2018-03-11
 */
public class ToolsEntityMetaObjectHandler extends MetaObjectHandler {

    @Value("${mybatis-plus.global-config.logic-not-delete-value}")
    private String logicNotDeleteValue;

    protected final static Logger logger = LoggerFactory.getLogger(ToolsEntityMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        // 新增时逻辑删除字段默认值，该默认值也可以通过数据库设置，此处先全局处理
        String testType = ((String) getFieldValByName("dataStatus", metaObject));
        if (StringUtils.isEmpty(testType)) {
            setFieldValByName("dataStatus", logicNotDeleteValue, metaObject);
        }
        // 全局处理公共字段 创建时间 private Date createtime
        if (metaObject.hasGetter("createtime")) {
            setFieldValByName("createtime", new Date(), metaObject);
        }
        // 最近更新人员 private String updator
        if (metaObject.hasGetter("updator")) {
            setFieldValByName("updator", "开发人员", metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 最近更新时间 private Date lastupdate
        if (metaObject.hasGetter("lastupdate")) {
            setFieldValByName("lastupdate", new Date(), metaObject);
        }
        // TODO 获取人员
        // 最近更新人员 private String updator
        if (metaObject.hasGetter("updator")) {
            setFieldValByName("updator", "开发人员", metaObject);
        }
    }
}
