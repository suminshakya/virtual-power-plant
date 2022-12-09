package com.proshore.vpps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MessageResponse {

    private String message;
    private HttpStatus status;
}
