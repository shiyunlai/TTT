package org.tis.tools.senior.module.developer.controller.request;


import lombok.Data;
import org.tis.tools.senior.module.developer.entity.SBranch;
import org.tis.tools.senior.module.developer.entity.SWorkitem;

@Data
public class WorkitemBranchDetailRequest {

    private SWorkitem workitem;

    private SBranch branch;
}
