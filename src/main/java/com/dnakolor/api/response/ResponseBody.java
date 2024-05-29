package com.dnakolor.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseBody<T> {
    private String message;
    private T body;
}
