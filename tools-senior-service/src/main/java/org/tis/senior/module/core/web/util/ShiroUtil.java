package org.tis.senior.module.core.web.util;

import org.apache.shiro.SecurityUtils;
import org.tis.senior.module.developer.entity.SSvnAccount;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/8/2
 **/
public class ShiroUtil {
    /**
     * 获取登录用户
     * @return
     */
    public static SSvnAccount getUser() {
        SSvnAccount principal = (SSvnAccount) SecurityUtils.getSubject().getPrincipal();
        principal.setSvnPwd(null);
        return principal;
    }
}
