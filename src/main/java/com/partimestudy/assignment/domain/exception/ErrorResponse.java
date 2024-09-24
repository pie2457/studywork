package com.partimestudy.assignment.domain.exception;

public record ErrorResponse(
    int statusCode,
    String message) {

}
