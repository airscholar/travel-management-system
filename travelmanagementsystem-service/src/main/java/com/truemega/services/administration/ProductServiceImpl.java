package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.ProductTypeDTO;
import com.truemega.entities.ProductType;
import com.truemega.interfaces.administration.ProductService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class ProductServiceImpl implements ProductService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public ProductTypeDTO saveProduct(ProductTypeDTO productTypeDTO,
			String userName) {

		loggerService
				.logServiceInfo("Start  saveProduct Method with  productTypeDTO "
						+ productTypeDTO + "userName  " + userName);
		try {

			ProductType productType = mapper.map(productTypeDTO,
					ProductType.class);
			baseDao.saveEntity(productType);
			loggerService.logServiceInfo("End  saveProduct Method");
			return mapper.map(productType, ProductTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveProduct", e);
			return null;
		}
	}

	@Override
	public ProductTypeDTO updateProduct(ProductTypeDTO productTypeDTO,
			String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  updateProduct Method with  productTypeDTO "
						+ productTypeDTO + "userName  " + userName);
		try {

			ProductType productType = mapper.map(productTypeDTO,
					ProductType.class);
			baseDao.updateEntity(productType);
			loggerService.logServiceInfo("End  updateProduct Method");
			return mapper.map(productType, ProductTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateProduct", e);
			return null;
		}
	}

	@Override
	public ProductTypeDTO findProductById(Integer productId, String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  findProductById Method with  productId "
						+ productId + "userName  " + userName);
		try {

			ProductType productType = baseDao.findEntityById(ProductType.class,
					productId);
			loggerService.logServiceInfo("End  findProductById Method");
			return mapper.map(productType, ProductTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findProductById", e);
			return null;

		}
	}

	@Override
	public List<ProductTypeDTO> getAllProduct(String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  getAllProduct Method with userName  "
						+ userName);

		try {

			String query = "select model from ProductType model";
			List<ProductType> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllProduct Method");
			return mapper.mapAsList(result, ProductTypeDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllProduct ", e);
			return null;
		}
	}

	@Override
	public void changeStatus(Boolean status, Integer productId, String userName) {
		loggerService
				.logServiceInfo("Start changeStatus  Method with status == "
						+ status + " and productId == " + productId
						+ "user name " + userName);
		try {
			String query = "update ProductType model set model.status ="
					+ status + " where model.id=" + productId;
			baseDao.executeDynamicQuery(query, ProductType.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	}

	@Override
	public boolean checkUniqueProductWithService(String productName,
			Integer serviceId) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  checkUniqueProductWithService Method with  productName "
						+ productName);
		try {

			// SELECT * from PRODUCT_TYPE where NAME like '%s%' and SERVICE_ID =
			// 1;
			String query = "select model FROM ProductType model where lower(model.name) = lower( '"
					+ productName + "') and  model.serviceId.id = " + serviceId;

			System.out.println("qqqqqqqq ==========" + query);
			List<ProductType> list = baseDao.findListByQuery(query);
			loggerService
					.logServiceInfo("End  checkUniqueProductWithService Method");
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
	public List<ProductTypeDTO> getAllProductActive(String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  getAllProduct Method with userName  "
						+ userName);

		try {

			String query = "select model from ProductType model where model.status = 1 ";
			List<ProductType> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllProduct Method");
			return mapper.mapAsList(result, ProductTypeDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllProduct ", e);
			return null;
		}
	
	}

}
