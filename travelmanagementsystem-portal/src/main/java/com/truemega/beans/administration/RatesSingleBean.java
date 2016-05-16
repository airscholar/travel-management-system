package com.truemega.beans.administration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelSingleBean;
import com.truemega.converter.AirLineConverter;
import com.truemega.converter.RoomConverter;
import com.truemega.converter.ServiceConverter;
import com.truemega.converter.SupplierProductConverter;
import com.truemega.dto.AirlineDTO;
import com.truemega.dto.RatesDTO;
import com.truemega.dto.RoomTypeDTO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.dto.SupplierProductDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.AirLineService;
import com.truemega.interfaces.administration.ProductSupplierService;
import com.truemega.interfaces.administration.RatesService;
import com.truemega.interfaces.administration.RoomTypeService;
import com.truemega.interfaces.administration.ServiceService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "ratessingle")
@ViewScoped
public class RatesSingleBean extends TravelSingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ServiceService serviceService;

	@EJB
	ProductSupplierService productSupplierService;

	@EJB
	RatesService ratesService;

	@EJB
	RoomTypeService roomTypeService;

	@EJB
	private AirLineService airLineService;

	private ServiceConverter serviceConverter;
	private SupplierProductConverter supplierProductConverter;
	private RoomConverter roomConverter;
	private AirLineConverter airLineConverter;

	private String year;

	private LoggerService loggerService = new LoggerService();

	private RatesDTO ratesDTO = new RatesDTO();

	private ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
	private List<SupplierProductDTO> supplierProductDTOs = new ArrayList<SupplierProductDTO>();

	private List<RoomTypeDTO> roomTypeDTOs = new ArrayList<RoomTypeDTO>();
	private List<AirlineDTO> airlineDTOs = new ArrayList<AirlineDTO>();

	public ServiceTypeDTO getServiceTypeDTO() {
		return serviceTypeDTO;
	}

	public void setServiceTypeDTO(ServiceTypeDTO serviceTypeDTO) {
		this.serviceTypeDTO = serviceTypeDTO;
	}

	public RatesDTO getRatesDTO() {
		return ratesDTO;
	}

	public void setRatesDTO(RatesDTO ratesDTO) {
		this.ratesDTO = ratesDTO;
	}

	private List<ServiceTypeDTO> serviceTypeDTOs;

	private Integer rateId;

	public void changeService(AjaxBehaviorEvent event) {

		loggerService.logPortalInfo(getUserName() + ">> Start changeService()");
		ServiceTypeDTO serviceTypeDTO = (ServiceTypeDTO) event.getComponent()
				.getAttributes().get("serviceObj");
		if (serviceTypeDTO != null) {

			// call el service
			supplierProductDTOs = productSupplierService
					.getAllSupplierProductsByServiceId(serviceTypeDTO.getId());
			supplierProductConverter = new SupplierProductConverter(
					supplierProductDTOs);
			System.out.println("supplierProductDTOs ==== "
					+ supplierProductDTOs.size());

		} else {

		}

	}

	@PostConstruct
	public void init() {

		Screen screen = (Screen) HttpJSFUtils.getSession().getAttribute(
				"screen");
		switch (screen.getScreenMode()) {
		case 0:
			screenMode = UIOperation.ADD;
			break;
		case 1:
			screenMode = UIOperation.UPDATE;
			load();
			break;
		case 2:
			screenMode = UIOperation.VIEW;
			load();
			break;
		}
		serviceTypeDTOs = serviceService.getAllServices(getUserName());
		System.out.println("serviceTypeDTOs======= " + serviceTypeDTOs.size());
		serviceConverter = new ServiceConverter(serviceTypeDTOs);
		supplierProductConverter = new SupplierProductConverter(
				supplierProductDTOs);

		roomTypeDTOs = roomTypeService.getAllRoomTypeActive(getUserName());
		System.out.println("roomTypeDTOs======= " + roomTypeDTOs.size());
		roomConverter = new RoomConverter(roomTypeDTOs);
		airlineDTOs = airLineService.getAllAirlineActive(getUserName());
		airLineConverter = new AirLineConverter(airlineDTOs);
		System.out.println("airlineDTOs======= " + airlineDTOs.size());
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start save method of RatesSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				ratesDTO.setSystemUser(getUserName());
				ratesDTO.setModificationDate(new Date());
				ratesDTO.setYear(Integer.parseInt(year));
				ratesDTO = ratesService.saveRates(ratesDTO, getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save productTypeDTO ", e);
			}

		} else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
			operationMessage = "This Product already exist. ";
		}
		loggerService.logPortalInfo(" end save method of RatesSingleBean ");

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		loggerService.logPortalInfo(" start update method of RatesSingleBean ");
		boolean isValid = validate();
		if (isValid) {
			// Save Entity
			try {
				ratesDTO.setSystemUser(getUserName());
				ratesDTO.setModificationDate(new Date());
				ratesDTO.setYear(Integer.parseInt(year));
				ratesDTO = ratesService.updateRates(ratesDTO, getUserName());
				screenMode = UIOperation.VIEW;
			} catch (Exception e) {
				operationStatus = Status.FAIL;
				loggerService.logPortalError("can't update productTypeDTO ", e);
			}
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.SUCCESS;
		} else {
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "This Product is already exist.";
		}
		loggerService.logPortalInfo(" end update method of RatesSingleBean ");

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start load method of RatesSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				rateId = entity.getEntityId();

			ratesDTO = ratesService.findRatesById(rateId, getUserName());
			serviceTypeDTO = ratesDTO.getSupplierProductId().getProductId()
					.getServiceId();
			year = String.valueOf(ratesDTO.getYear());
			supplierProductDTOs = productSupplierService
					.getAllSupplierProductsByServiceId(serviceTypeDTO.getId());
			supplierProductConverter = new SupplierProductConverter(
					supplierProductDTOs);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logPortalError("can't load  ", e);
		}
		loggerService.logPortalInfo(" end load method of RatesSingleBean ");
	}

	@Override
	public boolean validate() {
		return true;
	}

	public ServiceConverter getServiceConverter() {
		return serviceConverter;
	}

	public void setServiceConverter(ServiceConverter serviceConverter) {
		this.serviceConverter = serviceConverter;
	}

	public List<ServiceTypeDTO> getServiceTypeDTOs() {
		return serviceTypeDTOs;
	}

	public void setServiceTypeDTOs(List<ServiceTypeDTO> serviceTypeDTOs) {
		this.serviceTypeDTOs = serviceTypeDTOs;
	}

	public List<SupplierProductDTO> getSupplierProductDTOs() {
		return supplierProductDTOs;
	}

	public void setSupplierProductDTOs(
			List<SupplierProductDTO> supplierProductDTOs) {
		this.supplierProductDTOs = supplierProductDTOs;
	}

	public SupplierProductConverter getSupplierProductConverter() {
		return supplierProductConverter;
	}

	public void setSupplierProductConverter(
			SupplierProductConverter supplierProductConverter) {
		this.supplierProductConverter = supplierProductConverter;
	}

	public Integer getRateId() {
		return rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public List<RoomTypeDTO> getRoomTypeDTOs() {
		return roomTypeDTOs;
	}

	public void setRoomTypeDTOs(List<RoomTypeDTO> roomTypeDTOs) {
		this.roomTypeDTOs = roomTypeDTOs;
	}

	public List<AirlineDTO> getAirlineDTOs() {
		return airlineDTOs;
	}

	public void setAirlineDTOs(List<AirlineDTO> airlineDTOs) {
		this.airlineDTOs = airlineDTOs;
	}

	public RoomConverter getRoomConverter() {
		return roomConverter;
	}

	public void setRoomConverter(RoomConverter roomConverter) {
		this.roomConverter = roomConverter;
	}

	public AirLineConverter getAirLineConverter() {
		return airLineConverter;
	}

	public void setAirLineConverter(AirLineConverter airLineConverter) {
		this.airLineConverter = airLineConverter;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
