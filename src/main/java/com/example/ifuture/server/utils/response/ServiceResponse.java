package com.example.ifuture.server.utils.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceResponse<T> {

    private HttpStatus httpStatus;
    private MessageServiceResponse responseMessage;
    private T responseObject;

    public ServiceResponse() {}

    public ServiceResponse(HttpStatus httpStatus, MessageServiceResponse responseMessage, T responseObject) {
        this.httpStatus = httpStatus;
        this.responseMessage = responseMessage;
        this.responseObject = responseObject;
    }

    public void setSuccessResponse() {
        this.setHttpStatus(HttpStatus.OK);
        this.setResponseMessage(MessageServiceResponse.OK);
        this.setResponseObject(responseObject);
    }

    public void setInternalServerError() {
        this.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        this.setResponseMessage(MessageServiceResponse.UNKNOWN_PROBLEM);
        this.setResponseObject(responseObject);
    }
}
