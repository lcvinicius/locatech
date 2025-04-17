package br.com.fiap.locatech.locatech.controllers.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.locatech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.locatech.locatech.dtos.ValidationErrorDTO;
import br.com.fiap.locatech.locatech.services.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ControlerExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class) 
public ResponseEntity<ResourceNotFoundDTO> handleResourceNotFoundException(ResourceNotFoundException e) { 
    var resourceNotFoundDTO = new ResourceNotFoundDTO(e.getMessage(), 404);
    return ResponseEntity.status(404).body(resourceNotFoundDTO);
}


@ExceptionHandler(MethodArgumentNotValidException.class) 
public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) { 
    var status = HttpStatusCode.valueOf(400);
    List<String> errors = new ArrayList<String>();
    for(var error: e.getBindingResult().getFieldErrors()){
        errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
}

}
