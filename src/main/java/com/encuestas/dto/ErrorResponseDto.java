package com.encuestas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto  implements Serializable {
    private String message;
    private int statusCode;
    private String pathUrl;
}
