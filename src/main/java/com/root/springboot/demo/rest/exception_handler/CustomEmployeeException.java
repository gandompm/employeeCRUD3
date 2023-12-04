package com.root.springboot.demo.rest.exception_handler;

public class CustomEmployeeException extends RuntimeException{
    private EmployeeErrorResponse errorResponse;

    public CustomEmployeeException(EmployeeErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public EmployeeErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
