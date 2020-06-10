package com.skeleton.gateway.service.web.controller;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.user.service.api.IUserService;
import com.skeleton.user.service.api.dto.UserCreateDTO;
import com.skeleton.user.service.api.dto.UserUpdateDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxhsea
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("创建用户")
    @PostMapping("/create")
    public ResultDTO<Void> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @ApiOperation("更新用户")
    @PostMapping("/update")
    public ResultDTO<Void> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }
}
