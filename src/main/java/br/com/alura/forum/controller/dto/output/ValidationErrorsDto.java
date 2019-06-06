package br.com.alura.forum.controller.dto.output;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsDto {
	private List<FieldErrorOutputDto> errors = new ArrayList<>();
	private List<String> globalErrors = new ArrayList<String>();

	public List<FieldErrorOutputDto> getErrors() {
		return errors;
	}

	public List<String> getGlobalErrors() {
		return globalErrors;
	}



	public void addFieldError(String field, String message) {
		FieldErrorOutputDto fieldErrorDto = new FieldErrorOutputDto(field, message);
		errors.add(fieldErrorDto);
	}

	public void addError(String message) {
		globalErrors.add(message);
	}

	public int getNumberOfErrors() {
		return this.errors.size();
	}

}
