package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.SupplierProductDTO;
import com.truemega.interfaces.administration.ProductSupplierService;



@ManagedBean(name = "productsupplierlist")
@ViewScoped
public class ProductSupplierListBean  extends TravelListBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	private ProductSupplierService productSupplierService ;
	
	 private List<SupplierProductDTO> supplierProductDTOs ;

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return "administration/productsupplierlist.xhtml";
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
			supplierProductDTOs = productSupplierService.getAllSupplierProducts(getUserName());
			System.out.println("supplierProductDTOs ======  " + supplierProductDTOs.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}
	}
	
	public void changeStatus(AjaxBehaviorEvent event) {

		try {
			SupplierProductDTO supplierProductDTO  = (SupplierProductDTO) event.getComponent()
					.getAttributes().get("supplierProductmodel");

			productSupplierService.changeStatus(supplierProductDTO.getStatus(), supplierProductDTO.getId(),
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



	public List<SupplierProductDTO> getSupplierProductDTOs() {
		return supplierProductDTOs;
	}



	public void setSupplierProductDTOs(List<SupplierProductDTO> supplierProductDTOs) {
		this.supplierProductDTOs = supplierProductDTOs;
	}

}
