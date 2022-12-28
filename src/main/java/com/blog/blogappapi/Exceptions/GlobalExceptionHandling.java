package com.blog.blogappapi.Exceptions;

import com.blog.blogappapi.payloads.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
            String message  = ex.getMessage();
            ApiResponse apiResponse = new ApiResponse(message,false);
            return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)  // valid waale ke liye
    public ResponseEntity<Map<String , String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
            Map<String , String> resp = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error)->{
                String fieldName = ((FieldError)error).getField();  // objectError ko Typecast kr rahe fieldError
                String message = error.getDefaultMessage();
                resp.put(fieldName,message);

            });
            return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);

    }
}
