package com.truemega.services.administration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.TravelUserDTO;
import com.truemega.dto.TraveluserScreensDTO;
import com.truemega.dto.SystemScreensDTO;
import com.truemega.entities.TravelUser;
import com.truemega.entities.TraveluserScreens;
import com.truemega.entities.SystemScreens;
import com.truemega.interfaces.administration.TMSUserService;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;
import com.truemega.menu.TMSEmployeeService;

@Stateless
public class TMSUserServiceImpl implements TMSUserService {

	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	@EJB
	private TMSEmployeeService employeeService;

	private LoggerService loggerService = new LoggerService();

	@Override
	public void saveEmployee(TravelUserDTO employeeDTO, String userName) {

		loggerService
				.logServiceInfo("Start save TravelUser Method with  employeeDTO "
						+ employeeDTO + "userName  " + userName);
		try {
			TravelUser employee = mapper.map(employeeDTO, TravelUser.class);
			employeeDTO = mapper.map(baseDao.updateEntity(employee),
					TravelUserDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("can't  save Employee", e);
		}
		loggerService.logServiceInfo("End save TravelUser Method");
	}

	public void updateEmployee(TravelUserDTO employeeDTO, String userName) {

		loggerService
				.logServiceInfo("Start updateEmployee Method with  employeeDTO "
						+ employeeDTO + "userName  " + userName);
		try {
			try {
				TravelUser employee = mapper.map(employeeDTO, TravelUser.class);
				employeeDTO = mapper.map(baseDao.updateEntity(employee),
						TravelUserDTO.class);
			} catch (Exception e) {
				loggerService.logServiceError("can't  save Employee", e);
			}
		} catch (Exception e) {
			loggerService.logServiceError("can't  update Employee ", e);
		}
		loggerService.logServiceInfo("End updateEmployee Method    ");
	}

	@Override
	public TravelUserDTO findEmployeeById(Integer employeeId, String userName) {
		loggerService
				.logServiceInfo("Start findEmployeeById Method with  employeeId "
						+ employeeId + "userName  " + userName);
		TravelUserDTO result = null;
		try {
			TravelUser employee = baseDao.findEntityById(TravelUser.class,
					employeeId);
			result = mapper.map(employee, TravelUserDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("can't findEmployeeById  ", e);
		}
		loggerService.logServiceInfo("End findEmployeeById  Method    ");
		return result;
	}

	@Override
	public TravelUserDTO findEmployeeByStaffID(Integer employeeId,
			String userName) {
		loggerService
				.logServiceInfo("Start findEmployeeByStaffID Method with  employeeId "
						+ employeeId + "userName  " + userName);
		TravelUserDTO result = null;
		try {
			String query = "select e from TravelUser e where e.staffid = "
					+ employeeId;
			TravelUser employee = baseDao.findEntityByQuery(query);
			result = mapper.map(employee, TravelUserDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("can't findEmployeeByStaffID  ", e);
			e.printStackTrace();
		}
		loggerService.logServiceInfo("End findEmployeeByStaffID  Method    ");
		return result;
	}

	@Override
	public void delete(Integer travelUserId, String userName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatus(Boolean status, Integer userId, String userName) {
		loggerService.logServiceInfo("Start changeStatus Method with  status "
				+ status + "userName  " + userName);
		try {

			String query = "update TravelUser model set model.status ="
					+ status + " where model.employeeId=" + userId;
			baseDao.executeDynamicQuery(query, TravelUser.class, true);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't  changeStatus   ", e);
		}
		loggerService.logServiceInfo("End changeStatus  Method    ");
	}

	@Override
	public List<TraveluserScreensDTO> findAllUserPrivileges(
			Integer travelUserID, String userName) {
		loggerService
				.logServiceInfo("Start findAllUserPrivileges Method with  travelUserID "
						+ travelUserID + "userName  " + userName);

		List<TraveluserScreens> employeeScreens = null;
		List<TraveluserScreensDTO> employeeScreensDTOs = null;
		try {

			String query = "select es from TraveluserScreens es where es.employee.employeeId = :employeeId";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("employeeId", travelUserID);
			employeeScreens = baseDao.findListByQuery(query, parameters);
			employeeScreensDTOs = mapper.mapAsList(employeeScreens,
					TraveluserScreensDTO.class);
			loggerService
					.logServiceInfo("End findAllUserPrivileges  Method    ");
			return employeeScreensDTOs;

		} catch (Exception e) {
			loggerService.logServiceError("can't  findAllUserPrivileges   ", e);
			return null;
		}

	}

	@Override
	public List<SystemScreensDTO> findAllSystemScreens(Integer employeeId,
			String userName) {
		loggerService
				.logServiceInfo("Start findAllSystemScreens Method with  employeeId "
						+ employeeId + "userName  " + userName);
		List<SystemScreensDTO> systemScreensDTOs = new ArrayList<SystemScreensDTO>();
		List<SystemScreens> systemScreens;
		try {
			// List<Integer> screensIDs = null;
			// String firstQuery =
			// "select e.screen.screenId from TraveluserScreens e where e.employee.employeeId="
			// + employeeId;
			// screensIDs = baseDao.findListByQuery(firstQuery);
			String query = "select s from SystemScreens as s where s.screenId NOT IN (select e.screen.screenId from TraveluserScreens e where e.employee.employeeId="
					+ employeeId + ") ";
			Map<String, Object> parameters = new HashMap<String, Object>();
			// parameters.put("empScreensList", screensIDs);
			systemScreens = baseDao.findListByQuery(query, parameters);
			systemScreensDTOs = mapper.mapAsList(systemScreens,
					SystemScreensDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("can't  findAllSystemScreens   ", e);
		}
		loggerService.logServiceInfo("End findAllSystemScreens  Method    ");
		return systemScreensDTOs;
	}

	@Override
	public void saveEmployeeScreen(TraveluserScreensDTO employeeScreensDTO,
			String userName) {

		loggerService
				.logServiceInfo("Start saveEmployeeScreen Method with  employeeScreensDTO "
						+ employeeScreensDTO + "userName  " + userName);
		TraveluserScreens employeeScreens = null;
		try {
			employeeScreens = mapper.map(employeeScreensDTO,
					TraveluserScreens.class);
			SystemScreens systemScreens = getSystemScreenByID(
					employeeScreensDTO.getScreen().getScreenId(), userName);
			employeeScreens.setScreen(systemScreens);
			baseDao.saveEntity(employeeScreens);
		} catch (Exception e) {
			loggerService.logServiceError("can't  save EmployeeScreen   ", e);
		}
		loggerService.logServiceInfo("End save EmployeeScreen  Method    ");
	}

	@Override
	public SystemScreens getSystemScreenByID(Integer screenId, String userName) {
		loggerService
				.logServiceInfo("Start findAllSystemScreens Method with  screenId "
						+ screenId + "userName  " + userName);
		SystemScreens systemScreens = null;
		try {
			systemScreens = baseDao.findEntityById(SystemScreens.class,
					screenId);
		} catch (Exception e) {
			loggerService.logServiceError("can't getSystemScreenByID ", e);
		}
		loggerService.logServiceInfo("End  findAllSystemScreens  Method    ");
		return systemScreens;
	}

	@Override
	public void updateEmployeeScreen(TraveluserScreensDTO employeeScreensDTO,
			String userName) {
		loggerService
				.logServiceInfo("Start updateEmployeeScreen Method with  employeeScreensDTO "
						+ employeeScreensDTO + "userName  " + userName);

		TraveluserScreens employeeScreens = null;
		try {
			employeeScreens = mapper.map(employeeScreensDTO,
					TraveluserScreens.class);
			SystemScreens systemScreens = getSystemScreenByID(
					employeeScreensDTO.getScreen().getScreenId(), userName);
			employeeScreens.setScreen(systemScreens);
			baseDao.updateEntity(employeeScreens);
		} catch (Exception e) {
			loggerService.logServiceError("can't updateEmployeeScreen ", e);
		}
		loggerService.logServiceInfo("End updateEmployeeScreen Method");
	}

	@Override
	public void deleteEmployeeScreen(TraveluserScreensDTO employeeScreensDTO,
			String userName) {
		loggerService
				.logServiceInfo("Start deleteEmployeeScreen Method with  employeeScreensDTO "
						+ employeeScreensDTO + "userName  " + userName);
		TraveluserScreens employeeScreens = null;
		try {
			employeeScreens = findEmployeeScreensByID(
					employeeScreensDTO.getId(), userName);
			baseDao.deleteEntity(employeeScreens);
		} catch (Exception e) {
			loggerService.logServiceError("can't deleteEmployeeScreen ", e);
		}
		loggerService.logServiceInfo("End  deleteEmployeeScreen  Method    ");
	}

	@Override
	public TraveluserScreens findEmployeeScreensByID(Integer Id, String userName) {
		loggerService
				.logServiceInfo("Start findSystemScreenByID Method with  Id "
						+ Id + "userName  " + userName);
		TraveluserScreens employeeScreens = null;
		try {
			employeeScreens = baseDao.findEntityById(TraveluserScreens.class,
					Id);
		} catch (Exception e) {
			loggerService.logServiceError("can't findEmployeeScreensByID ", e);
		}
		loggerService.logServiceInfo("End  findSystemScreenByID  Method    ");
		return employeeScreens;
	}

	@Override
	public SystemScreensDTO findSystemScreenByID(Integer screenId,
			String userName) {
		loggerService
				.logServiceInfo("Start findSystemScreenByID Method with  screenId "
						+ screenId + "userName  " + userName);
		SystemScreens systemScreens = null;
		SystemScreensDTO screensDTO = null;
		try {

			systemScreens = baseDao.findEntityById(SystemScreens.class,
					screenId);
			screensDTO = mapper.map(systemScreens, SystemScreensDTO.class);
			loggerService
					.logServiceInfo("End  findSystemScreenByID  Method    ");
			return screensDTO;

		} catch (Exception e) {
			loggerService.logServiceError("can't findSystemScreenByID ", e);
			return null;
		}
	}

	@Override
	public TraveluserScreensDTO findEmployeeScreensByEmployeeID(
			Integer employeeId, String userName) {
		loggerService
				.logServiceInfo("Start findEmployeeScreensByEmployeeID Method with  employeeId "
						+ employeeId + "userName  " + userName);

		TraveluserScreensDTO employeeScreensDTO = null;
		try {

			String query = "select e TraveluserScreens e where e.employee.employeeId = :employeeId";
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("employeeId", employeeId);
			TraveluserScreensDTO employeeScreens = baseDao.findEntityByQuery(
					query, parameters);
			employeeScreensDTO = mapper.map(employeeScreens,
					TraveluserScreensDTO.class);

		} catch (Exception e) {
			loggerService.logServiceError(
					"can't findEmployeeScreensByEmployeeID ", e);
		}
		loggerService
				.logServiceInfo("End  findEmployeeScreensByEmployeeID  Method    ");
		return employeeScreensDTO;
	}

	@Override
	public SystemScreensDTO findScreenByID(Integer Id, String userName) {
		loggerService
				.logServiceInfo("Start findCarUserById    Method with  Id "
						+ Id + "userName  " + userName);
		SystemScreensDTO result = null;
		try {
			SystemScreens screen = baseDao.findEntityById(SystemScreens.class,
					Id);
			result = mapper.map(screen, SystemScreensDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("can't findCarUserById ", e);
		}
		loggerService.logServiceInfo("End  findScreenByID  Method    ");
		return result;
	}

	@Override
	public List<TravelUserDTO> listAllEmployeeUsers(String userName) {
		loggerService.logServiceInfo("Start listAllEmployeeUsers    Method    "
				+ "userName  " + userName);
		try {

			String query = "select model from TravelUser model";
			List<TravelUser> result = baseDao.findListByQuery(query);
			loggerService
					.logServiceInfo("End  listAllEmployeeUsers  Method    ");
			return mapper.mapAsList(result, TravelUserDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			loggerService.logServiceError("can't listAllEmployeeUsers ", e);
			e.printStackTrace();
			return null;
		}
	}

}
