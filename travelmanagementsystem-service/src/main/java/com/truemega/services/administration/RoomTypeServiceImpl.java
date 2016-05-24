package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.RoomTypeDTO;
import com.truemega.dto.SupplierDTO;
import com.truemega.entities.RoomType;
import com.truemega.entities.Supplier;
import com.truemega.interfaces.administration.RoomTypeService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class RoomTypeServiceImpl implements RoomTypeService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public RoomTypeDTO saveRoomType(RoomTypeDTO roomTypeDTO, String userName) {

		loggerService
				.logServiceInfo("Start  saveRoomType Method with  roomTypeDTO "
						+ roomTypeDTO + "userName  " + userName);
		try {

			RoomType roomType = mapper.map(roomTypeDTO, RoomType.class);
			baseDao.saveEntity(roomType);
			loggerService.logServiceInfo("End  saveRoomType Method");
			return mapper.map(roomType, RoomTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveRoomType", e);
			return null;
		}

	}

	@Override
	public RoomTypeDTO updateRoomType(RoomTypeDTO roomTypeDTO, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  updateRoomType Method with  roomTypeDTO "
						+ roomTypeDTO + "userName  " + userName);
		try {

			RoomType roomType = mapper.map(roomTypeDTO, RoomType.class);
			baseDao.updateEntity(roomType);
			loggerService.logServiceInfo("End  updateRoomType Method");
			return mapper.map(roomType, RoomTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateRoomType", e);
			return null;
		}

	}

	@Override
	public RoomTypeDTO findRoomTypeById(Integer roomTypeId, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  findRoomTypeById Method with  roomTypeId "
						+ roomTypeId + "userName  " + userName);
		try {

			RoomType roomType = baseDao.findEntityById(RoomType.class,
					roomTypeId);
			loggerService.logServiceInfo("End  findRoomTypeById Method");
			return mapper.map(roomType, RoomTypeDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findRoomTypeById", e);
			return null;

		}

	}

	@Override
	public List<RoomTypeDTO> getAllRoomTypes(String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  getAllRoomTypes Method with userName  "
						+ userName);

		try {

			String query = "select model from RoomType model";
			List<RoomType> result = baseDao.findListByQuery(query);
			loggerService.logServiceInfo("End  getAllRoomTypes Method");
			return mapper.mapAsList(result, RoomTypeDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllRoomTypes ", e);
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
			String query = "update RoomType model set model.status =" + status
					+ " where model.id=" + id;
			baseDao.executeDynamicQuery(query, RoomType.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	}

	@Override
	public boolean checkUniqueRoomTypeName(String roomTypeName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  checkUniqueroomTypeName Method with  roomTypeName "
						+ roomTypeName);
		try {

			String query = "select model FROM RoomType model where lower(model.name) = lower( '"
					+ roomTypeName + "')";

			System.out.println("qqqqqqqq ==========" + query);
			List<RoomType> list = baseDao.findListByQuery(query);
			loggerService.logServiceInfo("End  checkUniqueroomTypeName Method");
			return list.size() > 0 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  checkUniqueroomTypeName", e);
			return false;
		}

	}

	@Override
	public RoomTypeDTO findRoomByName(String roomName, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  findRoomByName Method with  roomName "
						+ roomName);

		String query = "select model FROM RoomType model where lower(model.name) = lower( '"
				+ roomName + "')";

		System.out.println("qqqqqqqq ==========" + query);
		List<RoomType> list = baseDao.findListByQuery(query);
		loggerService.logServiceInfo("End  findRoomByName Method");

		if (list.size() == 0)
			return null;
		else
			return mapper.map(list.get(0), RoomTypeDTO.class);

	}

	@Override
	public List<RoomTypeDTO> getAllRoomTypeActive(String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  getAllRoomTypeActive Method with userName  "
						+ userName);

		try {

			String query = "select model from RoomType model where  model.status = 1";
			List<Supplier> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllSuppliers Method");
			return mapper.mapAsList(result, RoomTypeDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllRoomTypeActive ", e);
			return null;
		}
	
	
	}
}
