package com.truemega.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.truemega.dao.GenericDAO;
import com.truemega.dto.CategoryDTO;
import com.truemega.dto.TraveluserScreensDTO;
import com.truemega.entities.Category;
import com.truemega.entities.TraveluserScreens;
import com.truemega.logger.LoggerService;
import com.truemega.mapping.MappingFactory;

@Stateless
public class TMSMenuServiceImpl implements TMSMenuService {
	@EJB
	private GenericDAO baseDao;

	@EJB
	private MappingFactory mapper;

	private LoggerService loggerService = new LoggerService();

	@Override
	public List<CategoryDTO> listAllCategories(String username) {
		loggerService.logServiceInfo(" Start of listAllCategories Method  ");
		try {
			String query = "select c from Category c ORDER BY c.categoryOrder ASC";
			List<Category> result = baseDao.findListByQuery(query);
			loggerService.logServiceInfo(" End of listAllCategories Method  ");
			return mapper.mapAsList(result, CategoryDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("Error in listAllCategories ", e);
			return null;
		}

	}

	@Override
	public CategoryDTO getCategoryByID(Integer id, String username) {
		loggerService.logServiceInfo(" Start of getCategoryByID Method  ");
		try {
			Category result = baseDao.findEntityById(Category.class, id);
			loggerService.logServiceInfo(" End of getCategoryByID Method  ");
			return mapper.map(result, CategoryDTO.class);
		} catch (Exception e) {
			loggerService.logServiceError("Error in getCategoryByID ", e);
			return null;
		}

	}

	@Override
	public List<TraveluserScreensDTO> listAllScreenByCategoryAndEmployee(
			int categoryId, int employeeId, String username) {
		try {
			loggerService
					.logServiceInfo(" Start of listAllScreenByCategoryAndEmployee Method  ");
			String query = "select s from TraveluserScreens s "
					+ " where s.screen.category.categoryId=:categoryId "
					+ " and s.employee.employeeId=:employeeId "
					+ " ORDER BY s.screen.screenOrder ASC";

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("categoryId", categoryId);
			params.put("employeeId", employeeId);
			loggerService.logServiceInfo("Queryyyyyyyyyy =============== "
					+ query);

			List<TraveluserScreens> result = baseDao.findListByQuery(query,
					params);
			loggerService
					.logServiceInfo(" End of listAllScreenByCategoryAndEmployee Method  ");
			return mapper.mapAsList(
					result == null ? new ArrayList<TraveluserScreensDTO>()
							: result, TraveluserScreensDTO.class);
		} catch (Exception e) {
			loggerService
					.logServiceInfo(" Error listAllScreenByCategoryAndEmployee Method  ");
			return new ArrayList<TraveluserScreensDTO>();
		}
	}

	@Override
	public TraveluserScreensDTO checkScreenForEmployee(String screenName,
			int employeeId, String username) {
		try {
			loggerService
					.logServiceInfo(" Start of checkScreenForEmployee Method  ");
			String query = "select s from TraveluserScreens s "
					+ " where s.screen.screenURL='" + screenName + "'"
					+ " and s.employee.employeeId=" + employeeId;

			loggerService
					.logServiceInfo("*** Query for Getting User screen is:\n\t "
							+ query);

			List<TraveluserScreens> result = baseDao.findListByQuery(query);
			if (result != null && result.size() > 0)
				return mapper.map(result.get(0), TraveluserScreensDTO.class);

			return null;
		} catch (Exception e) {
			loggerService.logServiceError(" Error in  checkScreenForEmployee ",
					e);
			return null;
		}
	}
}
