package org.tis.tools.abf.module.sys.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tis.tools.abf.module.sys.entity.enums.ErrCodeKind;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;

/**
 * Created by chenchao
 * Created on 2018/6/1 11:01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysErrCodeRequest extends RestRequest{

    private String guid;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private ErrCodeKind errcodeKind;

    private String errCode;

    private String errMsg;

}
