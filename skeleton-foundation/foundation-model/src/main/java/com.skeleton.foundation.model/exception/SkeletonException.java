package com.skeleton.foundation.model.exception;


import com.skeleton.foundation.model.error.ErrorObject;

public class SkeletonException extends RuntimeException {

    private ErrorObject errorObject;

    public SkeletonException(ErrorObject errorObject) {
        super(errorObject.getMessage());
        this.errorObject = errorObject;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }

    public SkeletonException() {
        super();
    }
}
