package com.partimestudy.assignment.domain.exception;

public class InternalServerException extends BaseException {

    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
