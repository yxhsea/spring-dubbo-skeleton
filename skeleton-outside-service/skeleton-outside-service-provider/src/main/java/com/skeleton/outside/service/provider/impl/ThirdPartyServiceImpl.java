package com.skeleton.outside.service.provider.impl;

import com.skeleton.foundation.model.dto.ResultListDTO;
import com.skeleton.outside.service.api.IThirdParty;
import com.skeleton.outside.service.provider.invoke.ThirdPartyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author yxhsea
 */
public class ThirdPartyServiceImpl implements IThirdParty {

    @Autowired
    private ThirdPartyFeignClient thirdPartyFeignClient;

    @Override
    public ResultListDTO<Object> getThirdPartyMessage() {
        ResultListDTO<Object> resultListDTO = thirdPartyFeignClient.getThirdPartyMessage("94e8c881-26d3-46cd-bd8d-34ea9c39e98f");
        System.out.println(resultListDTO.getData().get(0));
        Map<String, Object> stringObjectMap = (Map<String, Object>) resultListDTO.getData().get(0);
        System.out.println(stringObjectMap.get("total_count"));
        System.out.println("===========================");
        return ResultListDTO.success(null);
    }
}
