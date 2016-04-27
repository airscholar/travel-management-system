package com.truemega.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.truemega.validators.DateValidator")
public class DateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		System.out.println("Calling Validator with value " + value);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date selectedDate = null;
		Date currentDate = null;
		try {
			selectedDate = formatter.parse(value.toString());
			currentDate = formatter.parse(formatter.format(new Date()));
			String dateType = (String) component.getAttributes()
					.get("dateType");
			Date otherDate= (Date) component.getAttributes()
					.get("otherDate");
			String otherDateName= (String) component.getAttributes()
					.get("otherDateName");
			if(dateType != null){
				if (dateType.equalsIgnoreCase("past")) {
					if (selectedDate.compareTo(currentDate) > 0) {
						FacesMessage msg = new FacesMessage(
								"Date Validation Error.",
								"The chosen date can't be past today ");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						throw new ValidatorException(msg);
					}
				}
				if (dateType.equalsIgnoreCase("future")) {
					if (selectedDate.compareTo(currentDate) < 0) {
						FacesMessage msg = new FacesMessage(
								"Date Validation Error.",
								"Selected Date Can't be After current Date ");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						throw new ValidatorException(msg);
					}
				}
			}
			if(otherDate != null && otherDateName != null){
				if (selectedDate.before(otherDate)) {
					FacesMessage msg = new FacesMessage(
							"Date Validation Error.",
							"Selected Date Can't be Before " + otherDateName);
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
