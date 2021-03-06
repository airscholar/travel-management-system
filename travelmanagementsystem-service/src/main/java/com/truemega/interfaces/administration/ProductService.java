package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.ProductTypeDTO;
import com.truemega.dto.ServiceTypeDTO;

@Remote
public interface ProductService {

	public ProductTypeDTO saveProduct(ProductTypeDTO productTypeDTO,
			String userName);

	public ProductTypeDTO updateProduct(ProductTypeDTO productTypeDTO,
			String userName);

	public ProductTypeDTO findProductById(Integer productId, String userName);

	public List<ProductTypeDTO> getAllProduct(String userName);

	public void changeStatus(Boolean status, Integer productId, String userName);

	public boolean checkUniqueProductWithService(String productName,
			Integer serviceId);

	public List<ProductTypeDTO> getAllProductActive(String userName);

	public List<ProductTypeDTO> getProductsByService(
			ServiceTypeDTO serviceTypeDTO);

}
