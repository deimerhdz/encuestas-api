package com.encuestas.controllers;

import com.encuestas.dto.ErrorResponseDto;
import com.encuestas.exceptions.UniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler({UniqueException.class})
    public ResponseEntity<ErrorResponseDto> uniqueException(HttpServletRequest peticion, UniqueException ex){

        return buildResponse(peticion, HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }


    ResponseEntity<ErrorResponseDto> buildResponse(HttpServletRequest request, HttpStatus status, String mensaje ){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto();
        errorResponseDTO.setMessage(mensaje);
        errorResponseDTO.setStatusCode(status.value());
        errorResponseDTO.setPathUrl(request.getRequestURI());
        return new ResponseEntity<>(errorResponseDTO,status);
    }

}
