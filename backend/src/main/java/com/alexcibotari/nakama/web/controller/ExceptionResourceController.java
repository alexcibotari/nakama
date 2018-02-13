package com.alexcibotari.nakama.web.controller;

import com.alexcibotari.nakama.exception.AbstractServiceException;
import com.alexcibotari.nakama.web.resource.exception.ServiceExceptionResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResourceController {

  @ExceptionHandler(AbstractServiceException.class)
  public ResponseEntity<ServiceExceptionResource> processConcurencyError(
    AbstractServiceException ex) {
    return ResponseEntity.badRequest().body(new ServiceExceptionResource(ex));
  }
}
