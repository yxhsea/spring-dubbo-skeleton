package com.skeleton.user.service.provider.impl;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.exception.SkeletonException;
import com.skeleton.user.service.api.IUserService;
import com.skeleton.user.service.api.dto.UserCreateDTO;
import com.skeleton.user.service.api.dto.UserUpdateDTO;
import org.springframework.stereotype.Component;

/**
 * @author yxhsea
 */
@Component
public class UserServiceImpl implements IUserService {
    @Override
    public ResultDTO<Void> createUser(UserCreateDTO userCreateDTO) throws SkeletonException {
        return ResultDTO.success();
    }

    @Override
    public ResultDTO<Void> updateUser(UserUpdateDTO userUpdateDTO) throws SkeletonException {
        return ResultDTO.success();
    }
}
