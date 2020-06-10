package com.skeleton.outside.service.provider.invoke;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.dto.ResultListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author yxhsea
 */
//@FeignClient(name = "", url = "${outside.url}")
@FeignClient(name = "test", url = "http://127.0.0.1:8181")
public interface ThirdPartyFeignClient {
    /**
     * 获取第三方信息
     * @param domainId
     * @return
     */
//    @RequestMapping(value = "/message", method = RequestMethod.GET, headers = {"content-type=application/json"})
//    ResultDTO<Void> getThirdPartyMessage(
//            @RequestHeader("Access-Token") String accessToken,
//            @RequestParam("type") Integer type
//    );
    @RequestMapping(value = "/v1/user/member/count", method = RequestMethod.GET, headers = {"content-type=application/json"})
    ResultListDTO<Object> getThirdPartyMessage(
            @RequestHeader("domain_id") String domainId
    );
}
