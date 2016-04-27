package com.truemega.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.entities.TravelUser;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class TMSEmployeeServiceImpl implements TMSEmployeeService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public TravelUserDTO findUserByUserName(String username, String caller) {
		try {

			String query = "select u from TravelUser u WHERE u.accountname = :accountname and u.status=:status";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("accountname", username);
			param.put("status", true);
			List<TravelUser> result = baseDao.findListByQuery(query, param);
			if (result != null && result.size() > 0)
				return mapper.map(result.get(0), TravelUserDTO.class);

		} catch (Exception e) {
			e.printStackTrace();
			loggerService.logServiceError("", e);
		}
		return null;
	}

	@Override
	public Boolean authenticateUser(String username, String password) {
		return true;
	}

}
