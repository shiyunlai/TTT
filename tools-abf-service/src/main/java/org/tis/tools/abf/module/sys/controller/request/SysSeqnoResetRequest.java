package org.tis.tools.abf.module.sys.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.sys.entity.enums.SeqnoReset;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;

/**
 * Created by chenchao
 * Created on 2018/6/1 14:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysSeqnoResetRequest extends RestRequest {

    private String seqName;

    private String seqKey;

    @NotBlank(message = "序号数不能为空")
    private String seqNo;

    @NotNull(message = "重置方式不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private SeqnoReset reset ;

    private String resetParams;
}
