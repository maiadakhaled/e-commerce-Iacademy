package com.example.e_commerce.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private HttpStatus status;
    private int code;
    LocalDateTime timeStamp;

}
