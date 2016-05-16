package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.AirlineDTO;
import com.truemega.entities.Airline;
import com.truemega.interfaces.administration.AirLineService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class AirLineServiceImpl implements AirLineService{
    
	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();
	
	
	@Override
	public AirlineDTO saveAirLine(AirlineDTO airlineDTO, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  saveAirLine Method with  airlineDTO "
						+ airlineDTO + "userName  " + userName);
		try {

			Airline airline = mapper.map(airlineDTO, Airline.class);
			baseDao.saveEntity(airline);
			loggerService.logServiceInfo("End  saveAirLine Method");
			return mapper.map(airline, AirlineDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveAirLine", e);
			return null;
		}

	
	}

	@Override
	public AirlineDTO updateAirLine(AirlineDTO airlineDTO, String userName) {
		// TODO Auto-generated method stub


		loggerService
				.logServiceInfo("Start  updateAirLine Method with  airlineDTO "
						+ airlineDTO + "userName  " + userName);
		try {

			Airline airline = mapper.map(airlineDTO, Airline.class);
			baseDao.updateEntity(airline);
			loggerService.logServiceInfo("End  updateAirLine Method");
			return mapper.map(airline, AirlineDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateAirLine", e);
			return null;
		}

	
	
	}

	@Override
	public AirlineDTO findAirLineById(Integer airLineId, String userName) {

		loggerService
				.logServiceInfo("Start  findSupplierById Method with  airLineId "
						+ airLineId + "userName  " + userName);
		try {

			Airline airline = baseDao.findEntityById(Airline.class,
					airLineId);
			loggerService.logServiceInfo("End  findSupplierById Method");
			return mapper.map(airline, AirlineDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findSupplierById", e);
			return null;

		}
	
	}

	@Override
	public List<AirlineDTO> getAllAirLines(String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  getAllAirLines Method with userName  "
						+ userName);

		try {

			String query = "select model from Airline model";
			List<Airline> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllAirLines Method");
			return mapper.mapAsList(result, AirlineDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllAirLines ", e);
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
			String query = "update Airline model set model.status ="
					+ status + " where model.id=" + id;
			baseDao.executeDynamicQuery(query, Airline.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	
	
	}

	@Override
	public boolean checkUniqueAirLineName(String airLineName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  checkUniqueairLineName Method with  airLineName "
						+ airLineName);
		try {

			// SELECT * from PRODUCT_TYPE where NAME like '%s%' and SERVICE_ID =
			// 1;
			String query = "select model FROM Airline model where lower(model.name) = lower( '"
					+ airLineName + "')";

			System.out.println("qqqqqqqq ==========" + query);
			List<Airline> list = baseDao.findListByQuery(query);
			loggerService
					.logServiceInfo("End  checkUniqueairLineName Method");
			return list.size() > 0 ? false : true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError(
					"can't  checkUniqueairLineName", e);
			return false;
		}
	
	
	}

	@Override
	public AirlineDTO findAirLineByName(String airLineName, String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  findAirLineByName Method with  roomName "
						+ userName);

		String query = "select model FROM Airline model where lower(model.name) = lower( '"
				+ airLineName + "')";

		System.out.println("Airline ==========" + query);
		List<Airline> list = baseDao.findListByQuery(query);
		loggerService.logServiceInfo("End  findAirLineByName Method");

		if (list.size() == 0)
			return null;
		else
			return mapper.map(list.get(0), AirlineDTO.class);

	}

	@Override
	public List<AirlineDTO> getAllAirlineActive(String userName) {
		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  getAllAirlineActive Method with userName  "
						+ userName);

		try {

			String query = "select model from Airline model where  model.status = 1";
			List<Airline> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllAirlineActive Method");
			return mapper.mapAsList(result, AirlineDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllAirlineActive ", e);
			return null;
		}
	
	
	}
	
	

}
