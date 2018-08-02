package org.tis.tools.abf.module.om.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.om.entity.enums.OmGender;
import org.tis.tools.abf.module.om.entity.enums.OmPaperType;
import org.tis.tools.abf.module.om.entity.enums.OmZipCode;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.model.web.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by chenchao
 * Created on 2018/6/6 14:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OmEmployeeAddRequest extends RestRequest {

    @NotBlank(message = "员工代码不能为空")
    private String empCode;

    @NotBlank(message = "员工姓名不能为空")
    private String empName;

    @NotBlank(message = "员工全名不能为空")
    private String empRealname;

    @NotNull(message = "性别不能为空")
    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmGender gender;

    @NotBlank(message = "主机构编号不能为空")
    private String guidOrg;

    @NotBlank(message = "基本岗位不能为空")
    private String guidPosition;

    private String guidEmpMajor;

    private Date indate;

    private Date outdate;

    private String mobileno;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmPaperType paperType;

    private String paperNo;

    private Date birthdate;

    private String htel;

    private String haddress;

    @JSONField(deserializeUsing = CommonEnumDeserializer.class)
    private OmZipCode hzipcode;

    private String guidOperator;

    private String userId;

    private String remark;
}
