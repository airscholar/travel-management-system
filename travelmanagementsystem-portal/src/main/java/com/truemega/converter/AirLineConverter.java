package com.truemega.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.truemega.dto.AirlineDTO;
import com.truemega.logger.LoggerService;

public class AirLineConverter implements Converter {


	private List<AirlineDTO> airlineDTOs;
	private LoggerService loggerService = new LoggerService();
	public AirLineConverter(List<AirlineDTO> airlineDTOs) {
		this.airlineDTOs = airlineDTOs;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsObject method of AirLineConverter ");
		if (value != null && value.trim().length() > 0
				&& (!value.equals("None")&& value.trim().length() > 0)) {
			try {
				AirlineDTO selectedAirline = new AirlineDTO();
				selectedAirline.setId(new Integer(value));
				for (Iterator iterator = airlineDTOs.iterator(); iterator
						.hasNext();) {
					AirlineDTO airlineDTO = (AirlineDTO) iterator.next();
					if (airlineDTO.equals(selectedAirline)){
						loggerService.logPortalInfo(" end getAsObject method of AirLineConverter ");
						return airlineDTO;
					}
						
				}
			} catch (NumberFormatException e) {
				loggerService.logPortalError("Not a valid Selected Accident", e);
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid theme."));
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsString  method of AirLineConverter ");
		if (object instanceof String)
			return null;
		if (object != null) {
			return String.valueOf(((AirlineDTO) object).getId());
		} else {
			return null;
		}

	}



}
