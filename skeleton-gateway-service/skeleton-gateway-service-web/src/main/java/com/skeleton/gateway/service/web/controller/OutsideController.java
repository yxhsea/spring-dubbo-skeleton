package com.skeleton.gateway.service.web.controller;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.dto.ResultListDTO;
import com.skeleton.outside.service.api.IThirdParty;
import com.skeleton.user.service.api.IUserService;
import com.skeleton.user.service.api.dto.UserCreateDTO;
import com.skeleton.user.service.api.dto.UserUpdateDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yxhsea
 */
@RestController
@RequestMapping("/outside")
public class OutsideController {

    @Autowired
    private IThirdParty iThirdParty;

    @ApiOperation("调用第三方服务")
    @GetMapping("/call")
    public ResultListDTO<Object> createUser() {
//        return ResultListDTO.success(null);
        return iThirdParty.getThirdPartyMessage();
    }

}
