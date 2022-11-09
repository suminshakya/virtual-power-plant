package com.proshore.vpps.custom_exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorMessage {

    private HttpStatus status;
    private String apiPath;
    private List<String> errors = new ArrayList<>();
}
