package com.skeleton.outside.service.api;

import com.skeleton.foundation.model.dto.ResultListDTO;

/**
 * @author yxhsea
 */
public interface IThirdParty {
    /**
     * 获取第三方平台信息
     * @return
     */
    ResultListDTO<Object> getThirdPartyMessage();
}
