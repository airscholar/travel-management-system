package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.SupplierDTO;
import com.truemega.entities.ProductType;
import com.truemega.entities.Supplier;
import com.truemega.interfaces.administration.SupplierService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class SupplierServiceImpl implements SupplierService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public SupplierDTO saveSupplier(SupplierDTO supplierDTO, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  saveSupplier Method with  supplierDTO "
						+ supplierDTO + "userName  " + userName);
		try {

			Supplier supplier = mapper.map(supplierDTO, Supplier.class);
			baseDao.saveEntity(supplier);
			loggerService.logServiceInfo("End  saveSupplier Method");
			return mapper.map(supplier, SupplierDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveSupplier", e);
			return null;
		}

	}

	@Override
	public SupplierDTO updateSupplier(SupplierDTO supplierDTO, String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  updateSupplier Method with  supplierDTO "
						+ supplierDTO + "userName  " + userName);
		try {

			Supplier supplier = mapper.map(supplierDTO, Supplier.class);
			baseDao.updateEntity(supplier);
			loggerService.logServiceInfo("End  updateSupplier Method");
			return mapper.map(supplier, SupplierDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateSupplier", e);
			return null;
		}

	}

	@Override
	public SupplierDTO findSupplierById(Integer supplierId, String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  findSupplierById Method with  supplierId "
						+ supplierId + "userName  " + userName);
		try {

			Supplier supplier = baseDao.findEntityById(Supplier.class,
					supplierId);
			loggerService.logServiceInfo("End  findSupplierById Method");
			return mapper.map(supplier, SupplierDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findSupplierById", e);
			return null;

		}
	}

	@Override
	public List<SupplierDTO> getAllSuppliers(String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  getAllSuppliers Method with userName  "
						+ userName);

		try {

			String query = "select model from Supplier model";
			List<Supplier> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllSuppliers Method");
			return mapper.mapAsList(result, SupplierDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllSuppliers ", e);
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
			String query = "update Supplier model set model.status ="
					+ status + " where model.id=" + id;
			baseDao.executeDynamicQuery(query, ProductType.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	
	}

	@Override
	public boolean checkUniqueSupplierName(String supplierName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  checkUniqueSupplierName Method with  supplierName "
						+ supplierName);
		try {

			// SELECT * from PRODUCT_TYPE where NAME like '%s%' and SERVICE_ID =
			// 1;
			String query = "select model FROM Supplier model where lower(model.name) = lower( '"
					+ supplierName + "')";

			System.out.println("qqqqqqqq ==========" + query);
			List<Supplier> list = baseDao.findListByQuery(query);
			loggerService
					.logServiceInfo("End  checkUniqueSupplierName Method");
			return list.size() > 0 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError(
					"can't  checkUniqueSupplierName", e);
			return false;
		}
	
	}

}
