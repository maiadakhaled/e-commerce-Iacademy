package com.example.e_commerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptions extends RuntimeException {
    private String message;
    private HttpStatus status;

}
