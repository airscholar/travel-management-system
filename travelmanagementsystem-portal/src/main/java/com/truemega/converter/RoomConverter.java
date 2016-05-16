package com.truemega.converter;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.RoomTypeDTO;
import com.truemega.logger.LoggerService;

public class RoomConverter implements Converter {


	private List<RoomTypeDTO> roomTypeDTOs;
	private LoggerService loggerService = new LoggerService();
	public RoomConverter(List<RoomTypeDTO> roomTypeDTOs) {
		this.roomTypeDTOs = roomTypeDTOs;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start getAsObject method of RoomConverter ");
		if (value != null && value.trim().length() > 0
				&& (!value.equals("None")&& value.trim().length() > 0)) {
			try {
				RoomTypeDTO selectedRoom = new RoomTypeDTO();
				selectedRoom.setId(new Integer(value));
				for (Iterator iterator = roomTypeDTOs.iterator(); iterator
						.hasNext();) {
					RoomTypeDTO roomTypeDTO = (RoomTypeDTO) iterator.next();
					if (roomTypeDTO.equals(selectedRoom)){
						loggerService.logPortalInfo(" end getAsObject method of RoomConverter ");
						return roomTypeDTO;
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
			return String.valueOf(((RoomTypeDTO) object).getId());
		} else {
			return null;
		}

	}



}
