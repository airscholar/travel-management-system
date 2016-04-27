package com.truemega.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.truemega.validators.CharacterValidator")
public class CharacterValidator implements Validator{
	private static final String CHAR_PATTERN = "^[a-zA-Z]*$";
	private Pattern pattern;
	private Matcher matcher;
	
	public CharacterValidator(){
		pattern = Pattern.compile(CHAR_PATTERN );
	}
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()&&!isArabic(value.toString())){
			
			FacesMessage msg = 
				new FacesMessage("Character validation failed.", 
						"Characters only");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}
		else{
			int length = value.toString().length();
			boolean max = length > 4 ;
			boolean min = length < 2 ; 
			if(min){
				FacesMessage msg = 
						new FacesMessage("Didgits validation failed.", 
								"Please, insert minimum 2 characters");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
			}
			if(max){
				FacesMessage msg = 
						new FacesMessage("Didgits validation failed.", 
								"Please, insert maximum 4 characters");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
			}
		}
	}
	private Boolean isArabic(String word){
		Boolean arabic = true;
		for(int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if (!(c >= 0x0600 && c <=0x06E0))
	            return false;
		}
		return arabic;
	}

}
