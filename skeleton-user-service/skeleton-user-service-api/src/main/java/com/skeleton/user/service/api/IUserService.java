package com.skeleton.user.service.api;

import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.exception.SkeletonException;
import com.skeleton.user.service.api.dto.UserCreateDTO;
import com.skeleton.user.service.api.dto.UserUpdateDTO;

/**
 * @author yxhsea
 */
public interface IUserService {
    /**
     * 创建用户
     * @param userCreateDTO
     * @return
     * @throws SkeletonException
     */
    ResultDTO<Void> createUser(UserCreateDTO userCreateDTO) throws SkeletonException;

    /**
     * 更新用户
     * @param userUpdateDTO
     * @return
     * @throws SkeletonException
     */
    ResultDTO<Void> updateUser(UserUpdateDTO userUpdateDTO) throws SkeletonException;
}
