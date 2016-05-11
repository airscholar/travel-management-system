package com.truemega.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.truemega.dto.SupplierDTO;
import com.truemega.logger.LoggerService;

public class SupplierConverter implements Converter {

	private List<SupplierDTO> supplierDTOs;
	private LoggerService loggerService = new LoggerService();
	public SupplierConverter(List<SupplierDTO> supplierDTOs) {
		this.supplierDTOs = supplierDTOs;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsObject method of ServiceConverter ");
		if (value != null && value.trim().length() > 0
				&& (!value.equals("None")&& value.trim().length() > 0)) {
			try {
				SupplierDTO selectedSupplier = new SupplierDTO();
				selectedSupplier.setId(new Integer(value));
				for (Iterator iterator = supplierDTOs.iterator(); iterator
						.hasNext();) {
					SupplierDTO serviceDTO = (SupplierDTO) iterator.next();
					if (serviceDTO.equals(selectedSupplier)){
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
			return String.valueOf(((SupplierDTO) object).getId());
		} else {
			return null;
		}

	}



}
