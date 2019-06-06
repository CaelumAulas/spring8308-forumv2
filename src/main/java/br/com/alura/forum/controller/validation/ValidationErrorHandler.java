package br.com.alura.forum.controller.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.forum.controller.dto.output.ValidationErrorsDto;

@RestControllerAdvice
public class ValidationErrorHandler {
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorsDto validationError(MethodArgumentNotValidException exception) {
    	List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    	List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
    	
    	ValidationErrorsDto validationErrorsDto = new ValidationErrorsDto();
    	
    	fieldErrors.forEach(fieldError -> 
    		validationErrorsDto.addFieldError(
    				fieldError.getField(), getCustomMessage(fieldError)));
    	globalErrors.forEach(error -> 
		validationErrorsDto.addError(
				getCustomMessage(error)));

    	
    	return validationErrorsDto;
    }
	
	private String getCustomMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
	
}
