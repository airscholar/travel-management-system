package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.MaxMinValuesDTO;
import com.truemega.entities.MaxMinValues;
import com.truemega.interfaces.administration.MaxMinService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class MaxMinServiceImpl implements MaxMinService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public MaxMinValuesDTO saveMaxMinValues(MaxMinValuesDTO maxMinValuesDTO,
			String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  saveMaxMinValues Method with  MaxMinValuesDTO "
						+ maxMinValuesDTO + "userName  " + userName);
		try {

			MaxMinValues MaxMinValues = mapper.map(maxMinValuesDTO,
					MaxMinValues.class);
			baseDao.saveEntity(MaxMinValues);
			loggerService.logServiceInfo("End  saveMaxMinValues Method");
			return mapper.map(MaxMinValues, MaxMinValuesDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveMaxMinValues", e);
			return null;
		}

	}

	@Override
	public MaxMinValuesDTO updateMaxMinValues(MaxMinValuesDTO maxMinValuesDTO,
			String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  updateMaxMinValues Method with  MaxMinValuesDTO "
						+ maxMinValuesDTO + "userName  " + userName);
		try {

			MaxMinValues maxMinValues = mapper.map(maxMinValuesDTO,
					MaxMinValues.class);
			baseDao.updateEntity(maxMinValues);
			loggerService.logServiceInfo("End  updateMaxMinValues Method");
			return mapper.map(maxMinValues, MaxMinValuesDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateMaxMinValues", e);
			return null;
		}

	}

	@Override
	public MaxMinValuesDTO findMaxMinValuesById(Integer maxMinValuesId,
			String userName) {

		loggerService
				.logServiceInfo("Start  findSupplierById Method with  MaxMinValuesId "
						+ maxMinValuesId + "userName  " + userName);
		try {

			MaxMinValues MaxMinValues = baseDao.findEntityById(
					MaxMinValues.class, maxMinValuesId);
			loggerService.logServiceInfo("End  findSupplierById Method");
			return mapper.map(MaxMinValues, MaxMinValuesDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findSupplierById", e);
			return null;

		}

	}

	@Override
	public List<MaxMinValuesDTO> getAllMaxMinValuess(String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  getAllMaxMinValuess Method with userName  "
						+ userName);

		try {

			String query = "select model from MaxMinValues model ORDER BY model.id";
			List<MaxMinValues> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllMaxMinValuess Method");
			return mapper.mapAsList(result, MaxMinValuesDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllMaxMinValuess ", e);
			return null;
		}

	}

	@Override
	public void changeStatus(Boolean status, Integer id, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start changeStatus  Method with status == "
						+ status + " and id == " + id + "user name " + userName);
		try {
			String query = "update MaxMinValues model set model.status ="
					+ status + " where model.id=" + id;
			baseDao.executeDynamicQuery(query, MaxMinValues.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	}

	@Override
	public boolean checkUniqueMaxMinValuesName(String MaxMinValuesName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  checkUniqueMaxMinValuesName Method with  MaxMinValuesName "
						+ MaxMinValuesName);
		try {

			// SELECT * from PRODUCT_TYPE where NAME like '%s%' and SERVICE_ID =
			// 1;
			String query = "select model FROM MaxMinValues model where lower(model.name) = lower( '"
					+ MaxMinValuesName + "')";

			System.out.println("qqqqqqqq ==========" + query);
			List<MaxMinValues> list = baseDao.findListByQuery(query);
			loggerService
					.logServiceInfo("End  checkUniqueMaxMinValuesName Method");
			return list.size() > 0 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  checkUniqueMaxMinValuesName",
					e);
			return false;
		}

	}

	@Override
	public MaxMinValuesDTO findMaxMinValuesByName(String MaxMinValuesName,
			String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  findMaxMinValuesByName Method with  roomName "
						+ userName);

		String query = "select model FROM MaxMinValues model where lower(model.name) = lower( '"
				+ MaxMinValuesName + "')";

		System.out.println("MaxMinValues ==========" + query);
		List<MaxMinValues> list = baseDao.findListByQuery(query);
		loggerService.logServiceInfo("End  findMaxMinValuesByName Method");

		if (list.size() == 0)
			return null;
		else
			return mapper.map(list.get(0), MaxMinValuesDTO.class);

	}

	@Override
	public List<MaxMinValuesDTO> getAllMaxMinValuesActive(String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  getAllMaxMinValuesActive Method with userName  "
						+ userName);

		try {

			String query = "select model from MaxMinValues model where  model.status = 1";
			List<MaxMinValues> result = baseDao.findListByQuery(query);

			loggerService
					.logServiceInfo("End  getAllMaxMinValuesActive Method");
			return mapper.mapAsList(result, MaxMinValuesDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService
					.logServiceError("can't  getAllMaxMinValuesActive ", e);
			return null;
		}

	}

	@Override
	public List<MaxMinValuesDTO> updateMaxsMinsValues(
			List<MaxMinValuesDTO> maxMinValuesDTOs, String userName)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			for (MaxMinValuesDTO maxMinValuesDTO : maxMinValuesDTOs) {
				MaxMinValues maxMinValues = mapper.map(maxMinValuesDTO,
						MaxMinValues.class);
				baseDao.updateEntity(maxMinValues);

			}
			return maxMinValuesDTOs;
		} catch (Exception e) {
			
			throw (e);
			
		}

	}

}
