package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.SupplierProductDTO;

@Remote
public interface ProductSupplierService {
	
	
	public SupplierProductDTO saveSupplierProduct(SupplierProductDTO supplierProductDTO , String userName);

	public SupplierProductDTO updateSupplierProduct(SupplierProductDTO supplierProductDTO , String userName);

	public SupplierProductDTO findSupplierProductById(Integer supplierProductId , String userName);

	public List<SupplierProductDTO> getAllSupplierProducts(String userName);

    public void changeStatus(Boolean status, Integer id,
 			String userName );
    public boolean checkUniqueSupplierProductName(String supplierProductName  );
	

}
