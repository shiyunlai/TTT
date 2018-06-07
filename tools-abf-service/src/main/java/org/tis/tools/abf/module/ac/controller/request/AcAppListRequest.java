package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/6/7 14:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcAppListRequest extends RestRequest{

    @NotNull(message = "数组不能为空!")
    private List guidList;
}
