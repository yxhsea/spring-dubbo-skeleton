package com.skeleton.foundation.assist.handler;

import com.skeleton.foundation.model.dto.AbstractResult;
import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.error.ErrorCodes;
import com.skeleton.foundation.model.error.ErrorObject;
import com.skeleton.foundation.model.exception.SkeletonException;

/**
 * @author yxhsea
 */
public class DefaultExceptionHandler {

    public static AbstractResult handleException(Throwable e) {
        ErrorObject errorObject = null;
        // 自定义异常
        if (e instanceof SkeletonException) {
            errorObject = ((SkeletonException) e).getErrorObject();
        } else {
            //未知异常
            errorObject = ErrorCodes.build(ErrorCodes.RPC_ERROR, "系统繁忙，请稍后重试");
        }
        return ResultDTO.failure(errorObject);
    }
}
