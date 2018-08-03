package org.tis.tools.abf.module.ac.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tis.tools.model.web.RestRequest;

import java.util.List;

/**
 * Created by chenchao
 * Created on 2018/8/1 9:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AcRoleFuncQueRequest<T> extends RestRequest {

    private List<T> allData;

    private List<T> oldData;

}
