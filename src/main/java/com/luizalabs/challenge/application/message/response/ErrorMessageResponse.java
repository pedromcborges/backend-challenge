package com.luizalabs.challenge.application.message.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageResponse {
    public Integer code;
    public String message;
    public List<String> fields;

    public static ErrorMessageResponse of(Integer code, String message) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.code = code;
        errorMessageResponse.message = message;

        return errorMessageResponse;
    }

    public static ErrorMessageResponse of(Integer code, List<String> fields) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.code = code;
        errorMessageResponse.fields = fields;

        return errorMessageResponse;
    }

}
