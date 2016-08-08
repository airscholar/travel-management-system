package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.SupplierProductDTO;
import com.truemega.entities.ProductType;
import com.truemega.entities.SupplierProduct;
import com.truemega.interfaces.administration.ProductSupplierService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class ProductSupplierServiceImpl implements ProductSupplierService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public SupplierProductDTO saveSupplierProduct(
			SupplierProductDTO supplierProductDTO, String userName) {
	

		loggerService
				.logServiceInfo("Start  saveSupplierProduct Method with  supplierProductDTO "
						+ supplierProductDTO + "userName  " + userName);
		try {

			SupplierProduct supplierProduct = mapper.map(supplierProductDTO,
					SupplierProduct.class);
			baseDao.saveEntity(supplierProduct);
			loggerService.logServiceInfo("End  saveSupplierProduct Method");
			return mapper.map(supplierProduct, SupplierProductDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveSupplierProduct", e);
			return null;
		}

	}

	@Override
	public SupplierProductDTO updateSupplierProduct(
			SupplierProductDTO supplierProductDTO, String userName) {
	

		loggerService
				.logServiceInfo("Start  updateSupplierProduct Method with  supplierProductDTO "
						+ supplierProductDTO + "userName  " + userName);
		try {

			SupplierProduct supplierProduct = mapper.map(supplierProductDTO,
					SupplierProduct.class);
			baseDao.updateEntity(supplierProduct);
			loggerService.logServiceInfo("End  updateSupplierProduct Method");
			return mapper.map(supplierProduct, SupplierProductDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateSupplierProduct", e);
			return null;
		}
	}

	@Override
	public SupplierProductDTO findSupplierProductById(
			Integer supplierProductId, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  findSupplierProductById Method with  supplierProductId "
						+ supplierProductId + "userName  " + userName);
		try {

			SupplierProduct supplierProduct = baseDao.findEntityById(SupplierProduct.class,
					supplierProductId);
			loggerService.logServiceInfo("End  findSupplierProductById Method");
			return mapper.map(supplierProduct, SupplierProductDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findSupplierProductById", e);
			return null;

		}
	
	
	}

	@Override
	public List<SupplierProductDTO> getAllSupplierProducts(String userName) {


		loggerService
				.logServiceInfo("Start  getAllSupplierProducts Method with userName  "
						+ userName);

		try {

			String query = "select model from SupplierProduct model";
			List<SupplierProduct> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllSupplierProducts Method");
			return mapper.mapAsList(result, SupplierProductDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllSupplierProducts ", e);
			return null;
		}
	
	
	
	}

	@Override
	public void changeStatus(Boolean status, Integer id, String userName) {
		// TODO Auto-generated method stub


		loggerService
				.logServiceInfo("Start changeStatus  Method with status == "
						+ status + " and id == " + id
						+ "user name " + userName);
		try {
			String query = "update SupplierProduct model set model.status ="
					+ status + " where model.id=" + id;
			baseDao.executeDynamicQuery(query, SupplierProduct.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	
	
	}


	@Override
	public SupplierProductDTO checkUniqueSupplierWithProduct(Integer supplierId,
			Integer productId) {
	

		loggerService
				.logServiceInfo("Start  checkUniqueSupplierWithProduct Method with  roomName "
						+ supplierId +"and productId  " +productId);

		String query = "select model FROM SupplierProduct model where model.supplierId.id =  "
				+ supplierId + " and  model.productId.id = " + productId ;

		
		List<SupplierProduct> list = baseDao.findListByQuery(query);
		loggerService.logServiceInfo("End  checkUniqueSupplierWithProduct Method");

		if (list.size() == 0)
			return null;
		else
			return mapper.map(list.get(0), SupplierProductDTO.class);

	
	}

	@Override
	public boolean checkUniqueSupplierProduct(Integer  productId , Integer supplierId ) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  checkUniqueSupplierProduct Method with  productId and  supplierId"
						+ productId + supplierId);
		try {

			// SELECT * from PRODUCT_TYPE where NAME like '%s%' and SERVICE_ID =
			// 1;
			String query = "select model FROM SupplierProduct model where lower(model.productId.id) = "
					+ productId + "and  lower ( model.supplierId.id ) = " + supplierId ;

			
			List<ProductType> list = baseDao.findListByQuery(query);
			loggerService
					.logServiceInfo("End  checkUniqueSupplierProduct Method");
			return list.size() > 0 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError(
					"can't  checkUniqueProductWithService", e);
			return false;
		}
	}

	@Override
	public List<SupplierProductDTO> getAllSupplierProductsByServiceId(
			Integer serviceId) {
		// TODO Auto-generated method stub



		loggerService
				.logServiceInfo("Start  getAllSupplierProductsByServiceId Method with serviceId  "
						+ serviceId);

		try {

			String query = "select model from SupplierProduct model where model.productId.serviceId.id = "+serviceId;
			List<SupplierProduct> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllSupplierProductsByServiceId Method");
			return mapper.mapAsList(result, SupplierProductDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllSupplierProductsByServiceId ", e);
			return null;
		}
	
	
	
	
	}


}
