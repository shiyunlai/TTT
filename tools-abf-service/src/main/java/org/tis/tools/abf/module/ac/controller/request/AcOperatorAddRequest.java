package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *@author wfl
 * @date 2018/05/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcOperatorAddRequest extends RestRequest {

    @NotBlank (message = "登录用户名不能为空")
    private String userId;

    private String password;

    private String operatorName;

    @NotBlank (message = "操作员状态不能为空")
    private String operatorStatus;

    private Date invalDate;

    @NotBlank (message = "认证模式不能为空")
    private String authMode;

    @NotNull(message = "锁定次数限制不能为空")
    private BigDecimal lockLimit;

    private BigDecimal errCount;

    private Date lockTime;

    private Date unlockTime;

    private Date lastLogin;

    private Date startDate;

    private Date endDate;

    private String validTime;

    private String macCode;

    private String ipAddress;
}
