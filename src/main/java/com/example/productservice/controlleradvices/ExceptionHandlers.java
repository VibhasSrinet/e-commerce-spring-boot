package com.example.productservice.controlleradvices;
import com.example.productservice.Models.Product;
import com.example.productservice.dtos.ArthematicExceptionDto;
import com.example.productservice.dtos.ProductNotFoundDto;
import com.example.productservice.dtos.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.PortUnreachableException;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ArthematicExceptionDto> handleRunTimeException(){
        ArthematicExceptionDto arthematicExceptionDto = new ArthematicExceptionDto();
        arthematicExceptionDto.setMessage("Something went wrong!");
        return new ResponseEntity<>(arthematicExceptionDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundDto> handleProductNotFoundException(ProductNotFoundException exception){
        ProductNotFoundDto dto = new ProductNotFoundDto();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
