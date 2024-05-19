package com.spring.userservice.payload;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private boolean status;
    private String message;
    private HttpStatus httpStatus;
}
