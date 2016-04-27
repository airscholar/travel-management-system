package com.truemega.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.truemega.validators.PhoneValidator")
public class PhoneValidator implements Validator{

	private static final String PHONE_PATTERN = "([0-9])?(\\d{10})?"; // "([0-9])?(\\d{10})" 
	private Pattern pattern;
	private Matcher matcher;
	
	public PhoneValidator(){
		pattern = Pattern.compile(PHONE_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
			
			FacesMessage msg = 
				new FacesMessage("Phone Number validation Failed.", 
						"Invalid phone number format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}
		
	}

}
