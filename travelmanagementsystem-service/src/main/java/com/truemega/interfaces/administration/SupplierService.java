package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.SupplierDTO;

@Remote
public interface SupplierService {

	public SupplierDTO saveSupplier(SupplierDTO supplierDTO , String userName);

	public SupplierDTO updateSupplier(SupplierDTO supplierDTO , String userName);

	public SupplierDTO findSupplierById(Integer supplierId , String userName);

	public List<SupplierDTO> getAllSuppliers(String userName);

    public void changeStatus(Boolean status, Integer id,
 			String userName );
    public boolean checkUniqueSupplierName(String supplierName  );
    
    public List<SupplierDTO> getAllSuppliersActive(String userName);
}
