package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.SupplierDTO;
import com.truemega.interfaces.administration.SupplierService;


@ManagedBean(name = "supplierlist")
@ViewScoped
public class SupplierListBean  extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB 
	private SupplierService supplierService ;
	 private List<SupplierDTO> supplierDTOs ;

   
	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return "administration/supplierlist.xhtml";
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
			supplierDTOs = supplierService.getAllSuppliers(getUserName());
			System.out.println("suppliers ======  " + supplierDTOs.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param args
	 */
 
	
	public void changeStatus(AjaxBehaviorEvent event) {

		try {
			SupplierDTO supplier = (SupplierDTO) event.getComponent()
					.getAttributes().get("suppliermodel");

			supplierService.changeStatus(supplier.getStatus(), supplier.getId(),
					getUserName());

			load();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public List<SupplierDTO> getSupplierDTOs() {
		return supplierDTOs;
	}

	public void setSupplierDTOs(List<SupplierDTO> supplierDTOs) {
		this.supplierDTOs = supplierDTOs;
	}
	
	
}
