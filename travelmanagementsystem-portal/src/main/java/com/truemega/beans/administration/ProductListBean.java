package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.ServiceTypeDTO;
import com.truemega.interfaces.administration.ProductService;
import com.truemega.interfaces.administration.ServiceService;

@ManagedBean(name = "productlist")
@ViewScoped
public class ProductListBean extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	ServiceService serviceService;
	@EJB
	ProductService productService;

	List<ServiceTypeDTO> serviceTypeDTOs;
	List<ProductTypeDTO> productTypeDTOs;

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return "administration/productlist.xhtml";
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@PostConstruct
	@Override
	public void load() {
		// TODO Auto-generated method stub
		try {
			serviceTypeDTOs = serviceService.getAllServices(getUserName());

			productTypeDTOs = productService.getAllProduct(getUserName());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void changeStatus(AjaxBehaviorEvent event) {

		try {
			ProductTypeDTO product = (ProductTypeDTO) event.getComponent()
					.getAttributes().get("productmodel");

			productService.changeStatus(product.getStatus(), product.getId(),
					getUserName());

			load();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	public List<ServiceTypeDTO> getServiceTypeDTOs() {
		return serviceTypeDTOs;
	}

	public void setServiceTypeDTOs(List<ServiceTypeDTO> serviceTypeDTOs) {
		this.serviceTypeDTOs = serviceTypeDTOs;
	}

	public List<ProductTypeDTO> getProductTypeDTOs() {
		return productTypeDTOs;
	}

	public void setProductTypeDTOs(List<ProductTypeDTO> productTypeDTOs) {
		this.productTypeDTOs = productTypeDTOs;
	}

}
