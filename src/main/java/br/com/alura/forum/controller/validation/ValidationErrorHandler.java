package br.com.alura.forum.controller.validation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.forum.controller.dto.output.ValidationErrorsDto;

@RestControllerAdvice
public class ValidationErrorHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsDto validationError(MethodArgumentNotValidException exception) {
    	List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    	
    	ValidationErrorsDto validationErrorsDto = new ValidationErrorsDto();
    	
    	fieldErrors.forEach(fieldError -> 
    		validationErrorsDto.addFieldError(
    				fieldError.getField(), fieldError.getDefaultMessage()));
    	
    	return validationErrorsDto;
    }
}
