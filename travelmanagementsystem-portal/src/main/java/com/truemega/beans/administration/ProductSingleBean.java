package com.truemega.beans.administration;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.converter.ServiceConverter;
import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.ProductService;
import com.truemega.interfaces.administration.ServiceService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "productsingle")
@ViewScoped
public class ProductSingleBean extends TravelSingleBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ProductService productService;
	@EJB
	ServiceService serviceService;

	private ServiceConverter serviceConverter;
	private LoggerService loggerService = new LoggerService();

	private ProductTypeDTO productTypeDTO = new ProductTypeDTO();
	private ProductTypeDTO oldproductTypeDTO = new ProductTypeDTO();

	private List<ServiceTypeDTO> serviceTypeDTOs;

	private Integer productId;

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
		
		serviceConverter = new ServiceConverter(serviceTypeDTOs);

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		loggerService.logPortalInfo(" start save method of ProductSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				productTypeDTO.setSystemUser(getUserName());
				productTypeDTO.setModificationDate(new Date());

				productTypeDTO = productService.saveProduct(productTypeDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save productTypeDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
			operationMessage = "This Product already exist. ";
		}
		loggerService.logPortalInfo(" end save method of ProductSingleBean ");
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		loggerService.logPortalInfo(" start update method of ProductSingleBean ");
		boolean isValid = validate();
		if (isValid) {
			// Save Entity
			try {
				productTypeDTO.setSystemUser(getUserName());
				productTypeDTO.setModificationDate(new Date());
				productService.updateProduct(productTypeDTO, getUserName());
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
			operationMessage = "This Product is already exist." ;
		}
		loggerService.logPortalInfo(" end update method of ProductSingleBean ");
		
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		loggerService
				.logPortalInfo(" start load method of ProductSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				productId = entity.getEntityId();

			productTypeDTO = productService.findProductById(productId,
					getUserName());

			
			oldproductTypeDTO = productTypeDTO ;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logPortalError("can't load  ", e);
		}
		loggerService.logPortalInfo(" end load method of ProductSingleBean ");
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub

		if (screenMode.equals(UIOperation.ADD)) {
			return productService.checkUniqueProductWithService(productTypeDTO.getName(),productTypeDTO.getServiceId().getId());
		} else {
			if (oldproductTypeDTO.getName().equals(productTypeDTO.getName()) && oldproductTypeDTO.getServiceId().getId() == productTypeDTO.getServiceId().getId())
				return true;
			else
				return false;
		}
	}

	public ProductTypeDTO getProductTypeDTO() {
		return productTypeDTO;
	}

	public void setProductTypeDTO(ProductTypeDTO productTypeDTO) {
		this.productTypeDTO = productTypeDTO;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductTypeDTO getOldproductTypeDTO() {
		return oldproductTypeDTO;
	}

	public void setOldproductTypeDTO(ProductTypeDTO oldproductTypeDTO) {
		this.oldproductTypeDTO = oldproductTypeDTO;
	}

}
