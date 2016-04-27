package com.truemega.menu;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.CategoryDTO;
import com.truemega.dto.TraveluserScreensDTO;

@Remote
public interface TMSMenuService {
	public List<CategoryDTO> listAllCategories(String username);

	public CategoryDTO getCategoryByID(Integer id, String username);

	public List<TraveluserScreensDTO> listAllScreenByCategoryAndEmployee(
			int categoryId, int employeeId, String username);

	TraveluserScreensDTO checkScreenForEmployee(String screenName, int employeeId,
			String username);
}
