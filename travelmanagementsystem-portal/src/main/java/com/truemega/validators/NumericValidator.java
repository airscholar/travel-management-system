package com.truemega.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.truemega.validators.NumericValidator")
public class NumericValidator implements Validator {
	private static final String DIGIT_PATTERN = "[0-9]*"; 
	private Pattern pattern;
	private Matcher matcher;

	public NumericValidator() {

	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		pattern = Pattern.compile(DIGIT_PATTERN);
		if (value != null) {
			matcher = pattern.matcher(value.toString());
			if (!matcher.matches()) {
				FacesMessage msg = new FacesMessage(
						"Digits validation failed.", "Digits only");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);

			}
		}

	}
}
