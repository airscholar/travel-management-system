package com.truemega.services.administration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.RatesDTO;
import com.truemega.entities.Rates;
import com.truemega.interfaces.administration.RatesService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class RatesServiceImpl implements RatesService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public RatesDTO saveRates(RatesDTO RatesDTO, String userName) {
		// TODO Auto-generated method stub

		loggerService.logServiceInfo("Start  saveRates Method with  RatesDTO "
				+ RatesDTO + "userName  " + userName);
		try {

			Rates Rates = mapper.map(RatesDTO, Rates.class);
			baseDao.saveEntity(Rates);
			loggerService.logServiceInfo("End  saveRates Method");
			return mapper.map(Rates, RatesDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  saveRates", e);
			return null;
		}

	}

	@Override
	public RatesDTO updateRates(RatesDTO RatesDTO, String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		loggerService
				.logServiceInfo("Start  updateRates Method with  RatesDTO "
						+ RatesDTO + "userName  " + userName);
		try {

			Rates Rates = mapper.map(RatesDTO, Rates.class);
			baseDao.updateEntity(Rates);
			loggerService.logServiceInfo("End  updateRates Method");
			return mapper.map(Rates, RatesDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  updateRates", e);
			return null;
		}

	}

	@Override
	public RatesDTO findRatesById(Integer RatesId, String userName) {
		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  findRatesById Method with  RatesId "
						+ RatesId + "userName  " + userName);
		try {

			Rates Rates = baseDao.findEntityById(Rates.class, RatesId);
			loggerService.logServiceInfo("End  findRatesById Method");
			return mapper.map(Rates, RatesDTO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  findRatesById", e);
			return null;

		}
	}

	@Override
	public List<RatesDTO> getAllRatess(String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  getAllRatess Method with userName  "
						+ userName);

		try {

			String query = "select model from Rates model";
			List<Rates> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllRatess Method");
			return mapper.mapAsList(result, RatesDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllRatess ", e);
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
			String query = "update Rates model set model.status =" + status
					+ " where model.id=" + id;
			baseDao.executeDynamicQuery(query, Rates.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus    ", e);
		}

		loggerService.logServiceInfo("End  changeStatus  Method  ");

	}

	@Override
	public List<RatesDTO> getAllRatessActive(String userName) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		loggerService
				.logServiceInfo("Start  getAllRatess Method with userName  "
						+ userName);

		try {

			String query = "select model from Rates model where  model.status = 1";
			List<Rates> result = baseDao.findListByQuery(query);

			loggerService.logServiceInfo("End  getAllRatess Method");
			return mapper.mapAsList(result, RatesDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			loggerService.logServiceError("can't  getAllRatess ", e);
			return null;
		}

	}

}
