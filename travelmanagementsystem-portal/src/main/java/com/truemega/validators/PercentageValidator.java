package com.truemega.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.truemega.validators.PercentageValidator")
public class PercentageValidator implements Validator{
	private static final String CHAR_PATTERN ="\\d+(\\.\\d+)?%";
	private Pattern pattern;
	private Matcher matcher;
	
	
	public PercentageValidator() {
		pattern = Pattern.compile(CHAR_PATTERN );
	}
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
			FacesMessage msg = 
				new FacesMessage("Paramater must be Percentage", 
						"Percentage only");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}
		
	}

}
