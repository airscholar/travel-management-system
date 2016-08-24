package com.truemega.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.truemega.dto.ServiceTypeDTO;
import com.truemega.logger.LoggerService;

public class ServiceConverter  implements Converter{

	private List<ServiceTypeDTO> services;
	private LoggerService loggerService = new LoggerService();
	public ServiceConverter(List<ServiceTypeDTO> services) {
		this.services = services;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsObject method of ServiceConverter ");
		if (value != null && value.trim().length() > 0
				&& (!value.equals("None")&& !value.equals("All")&&  value.trim().length() > 0)) {
			try {
				ServiceTypeDTO selectedService = new ServiceTypeDTO();
				selectedService.setId(new Integer(value));
				for (Iterator iterator = services.iterator(); iterator
						.hasNext();) {
					ServiceTypeDTO serviceDTO = (ServiceTypeDTO) iterator.next();
					if (serviceDTO.equals(selectedService)){
						loggerService.logPortalInfo(" end getAsObject method of ServiceConverter ");
						return serviceDTO;
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
		loggerService.logPortalInfo(" start getAsString  method of ServiceConverter ");
		if (object instanceof String)
			return null;
		if (object != null) {
			return String.valueOf(((ServiceTypeDTO) object).getId());
		} else {
			return null;
		}

	}

}
