package com.truemega.beans.administration;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.truemega.beans.TravelSingleBean;
import com.truemega.converter.ProductConverter;
import com.truemega.converter.SupplierConverter;
import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.SupplierDTO;
import com.truemega.dto.SupplierProductDTO;
import com.truemega.enums.Status;
import com.truemega.enums.UIOperation;
import com.truemega.interfaces.administration.ProductService;
import com.truemega.interfaces.administration.ProductSupplierService;
import com.truemega.interfaces.administration.SupplierService;
import com.truemega.logger.LoggerService;
import com.truemega.sessionbeans.Entity;
import com.truemega.sessionbeans.Screen;
import com.truemega.utils.HttpJSFUtils;

@ManagedBean(name = "productsuppliersingle")
@ViewScoped
public class ProductSupplierSingleBean extends TravelSingleBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoggerService loggerService = new LoggerService();
	
	@EJB
	private ProductSupplierService productSupplierService   ;
	
	
	@EJB
	private SupplierService  supplierService ;
	
	@EJB
	private ProductService  productService ;
	
	
	private ProductConverter productConverter ;
	private SupplierConverter supplierConverter ;
	
	private List<ProductTypeDTO> productTypeDTOs ;
	private List<SupplierDTO> supplierDTOs ;
	private Integer productSupplierId;
	private SupplierProductDTO supplierProductDTO = new SupplierProductDTO() ;
	
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
		productTypeDTOs = productService.getAllProduct(getUserName());
		System.out.println("productTypeDTOs ======= " + productTypeDTOs.size());
		productConverter = new ProductConverter(productTypeDTOs);
		
		supplierDTOs = supplierService.getAllSuppliers(getUserName());
		System.out.println("supplierDTOs ======= " + supplierDTOs.size());
		supplierConverter = new SupplierConverter(supplierDTOs);

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
		loggerService.logPortalInfo(" start save method of ProductSupplierSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				supplierProductDTO.setSystemUser(getUserName());
				supplierProductDTO.setModificationDate(new Date());

				supplierProductDTO = productSupplierService.saveSupplierProduct(supplierProductDTO,
						getUserName());
				screenMode = UIOperation.ADD;
				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't save supplierProductDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.ADD;
			operationStatus = Status.FAIL;
			operationMessage = "This Product Supplier already exist. ";
		}
		
		loggerService.logPortalInfo(" end save method of ProductSupplierSingleBean ");
		
	
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		loggerService.logPortalInfo(" start update method of ProductSupplierSingleBean ");
		boolean isValid = validate();
		if (isValid) {

			try {

				supplierProductDTO.setSystemUser(getUserName());
				supplierProductDTO.setModificationDate(new Date());

				supplierProductDTO = productSupplierService.updateSupplierProduct(supplierProductDTO,
						getUserName());

				screenMode = UIOperation.VIEW;
				operationStatus = Status.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				operationStatus = Status.FAIL;
				operationMessage = "Invalid Data";
				loggerService.logPortalError("can't update supplierProductDTO ", e);
			}
			
		}else {
			// Error Message
			screenMode = UIOperation.UPDATE;
			operationStatus = Status.FAIL;
			operationMessage = "This Product Supplier already exist. ";
		}
		
		loggerService.logPortalInfo(" end update method of ProductSupplierSingleBean ");
		
	
		
	}

	@Override
	public void load() {

		loggerService
				.logPortalInfo(" start load method of AirLineSingleBean ");
		try {
			Entity entity = (Entity) HttpJSFUtils.getSession().getAttribute(
					"entity");
			if (entity != null)
				productSupplierId = entity.getEntityId();

			supplierProductDTO = productSupplierService.findSupplierProductById(productSupplierId,
					getUserName());

			
			productTypeDTOs = productService.getAllProduct(getUserName()) ;
			System.out.println("productTypeDTOs ==== " + productTypeDTOs.size());
			
			supplierDTOs = supplierService.getAllSuppliers(getUserName()) ;
			System.out.println("supplierDTOs ==== " + supplierDTOs.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logPortalError("can't load  ", e);
		}
		loggerService.logPortalInfo(" end load method of AirLineSingleBean ");
	
	
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	public List<ProductTypeDTO> getProductTypeDTOs() {
		return productTypeDTOs;
	}

	public void setProductTypeDTOs(List<ProductTypeDTO> productTypeDTOs) {
		this.productTypeDTOs = productTypeDTOs;
	}

	public List<SupplierDTO> getSupplierDTOs() {
		return supplierDTOs;
	}

	public void setSupplierDTOs(List<SupplierDTO> supplierDTOs) {
		this.supplierDTOs = supplierDTOs;
	}

	public Integer getproductSupplierId() {
		return productSupplierId;
	}

	public void setproductSupplierId(Integer productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public SupplierProductDTO getSupplierProductDTO() {
		return supplierProductDTO;
	}

	public void setSupplierProductDTO(SupplierProductDTO supplierProductDTO) {
		this.supplierProductDTO = supplierProductDTO;
	}

	public ProductConverter getProductConverter() {
		return productConverter;
	}

	public void setProductConverter(ProductConverter productConverter) {
		this.productConverter = productConverter;
	}

	public SupplierConverter getSupplierConverter() {
		return supplierConverter;
	}

	public void setSupplierConverter(SupplierConverter supplierConverter) {
		this.supplierConverter = supplierConverter;
	}

	public Integer getProductSupplierId() {
		return productSupplierId;
	}

	public void setProductSupplierId(Integer productSupplierId) {
		this.productSupplierId = productSupplierId;
	}
	
	

}
