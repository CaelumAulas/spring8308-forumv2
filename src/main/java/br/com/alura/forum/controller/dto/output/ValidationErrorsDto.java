package br.com.alura.forum.controller.dto.output;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsDto {
	private List<FieldErrorOutputDto> errors = new ArrayList<>();

	public List<FieldErrorOutputDto> getErrors() {
		return errors;
	}
	
    public void addFieldError(String field, String message) {
    	FieldErrorOutputDto fieldErrorDto = new FieldErrorOutputDto(field, message);
		errors.add(fieldErrorDto );
    }
	
	public int getNumberOfErrors() {
		return this.errors.size();
	}
}
