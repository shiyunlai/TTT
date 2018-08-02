package org.tis.tools.security.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tis.tools.model.common.ResultVO;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/6/13
 **/
@FeignClient("abf-service")
public interface IAuthFeignService {

    @GetMapping("/acOperators/auth/{userId}")
    ResultVO loadUserByUserId(@PathVariable(value = "userId") String userId);

    @GetMapping("/acApp/auth/{code}")
    ResultVO loadClientByAppCode(@PathVariable(value = "code") String code);

}
