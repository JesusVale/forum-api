package com.jv.forum_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ErrorResponseDto {

    private String message;

    private HttpStatus status;

}
