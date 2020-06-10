package com.skeleton.foundation.web.handler;

import com.skeleton.foundation.model.dto.AbstractResult;
import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.error.ErrorCodes;
import com.skeleton.foundation.model.error.ErrorObject;
import com.skeleton.foundation.model.exception.SkeletonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
** web 异常处理
*
 * @author yxhsea*/
@RestControllerAdvice
public class WebExceptionHandler {

   private final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

   /**
    * 处理 SkeletonException
    *
    * @param skeletonException
    * @return
    */
   @ExceptionHandler(SkeletonException.class)
   public final AbstractResult handleSkeletonException(SkeletonException skeletonException) {
       logger.error("系统异常", skeletonException);
       ErrorObject errorObject = skeletonException.getErrorObject();
       return ResultDTO.failure(errorObject);
   }

   /**
    * 处理其它异常
    *
    * @param exception
    * @return
    */
   @ExceptionHandler(Exception.class)
   public final AbstractResult handleOtherExceptions(Exception exception) {
       logger.error("系统异常", exception);
       ErrorObject errorObject = ErrorCodes.build(ErrorCodes.SYSTEM_BUSY_ERROR, "系统繁忙，请稍后重试");
       return ResultDTO.failure(errorObject);
   }

}
