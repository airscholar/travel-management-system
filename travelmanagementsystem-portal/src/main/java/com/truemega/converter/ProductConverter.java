package com.truemega.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.truemega.dto.ProductTypeDTO;
import com.truemega.logger.LoggerService;

public class ProductConverter implements Converter {


	private List<ProductTypeDTO> productTypeDTOs;
	private LoggerService loggerService = new LoggerService();
	public ProductConverter(List<ProductTypeDTO> productTypeDTOs) {
		this.productTypeDTOs = productTypeDTOs;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsObject method of ServiceConverter ");
		if (value != null && value.trim().length() > 0
				&& (!value.equals("None")&& value.trim().length() > 0)) {
			try {
				ProductTypeDTO selectedProduct = new ProductTypeDTO();
				selectedProduct.setId(new Integer(value));
				for (Iterator iterator = productTypeDTOs.iterator(); iterator
						.hasNext();) {
					ProductTypeDTO productTypeDTO = (ProductTypeDTO) iterator.next();
					if (productTypeDTO.equals(selectedProduct)){
						loggerService.logPortalInfo(" end getAsObject method of ServiceConverter ");
						return productTypeDTO;
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
			return String.valueOf(((ProductTypeDTO) object).getId());
		} else {
			return null;
		}

	}



}
