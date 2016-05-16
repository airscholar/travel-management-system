package com.truemega.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.SupplierProductDTO;
import com.truemega.logger.LoggerService;

public class SupplierProductConverter implements Converter {


	private List<SupplierProductDTO> supplierProductDTOs;
	private LoggerService loggerService = new LoggerService();
	public SupplierProductConverter(List<SupplierProductDTO> supplierProductDTOs) {
		this.supplierProductDTOs = supplierProductDTOs;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsObject method of SupplierProductConverter ");
		if (value != null && value.trim().length() > 0
				&& (!value.equals("None")&& value.trim().length() > 0)) {
			try {
				SupplierProductDTO selectedSupplierProduct = new SupplierProductDTO();
				selectedSupplierProduct.setId(new Integer(value));
				for (Iterator iterator = supplierProductDTOs.iterator(); iterator
						.hasNext();) {
					SupplierProductDTO supplierProductDTO = (SupplierProductDTO) iterator.next();
					if (supplierProductDTO.equals(selectedSupplierProduct)){
						loggerService.logPortalInfo(" end getAsObject method of SupplierProductConverter ");
						return supplierProductDTO;
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
			return String.valueOf(((SupplierProductDTO) object).getId());
		} else {
			return null;
		}

	}



}
