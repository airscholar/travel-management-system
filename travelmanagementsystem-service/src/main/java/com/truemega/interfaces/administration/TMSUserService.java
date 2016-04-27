package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.TravelUserDTO;
import com.truemega.dto.TraveluserScreensDTO;
import com.truemega.dto.SystemScreensDTO;
import com.truemega.entities.TraveluserScreens;
import com.truemega.entities.SystemScreens;

@Remote
public interface TMSUserService {

	public void saveEmployee(TravelUserDTO employeeDTO, String userName);

	public void updateEmployee(TravelUserDTO employeeDTO, String userName);

	public TravelUserDTO findEmployeeById(Integer employeeId, String userName);

	public void delete(Integer travelUserId, String userName);

	public void changeStatus(Boolean status, Integer userId, String userName);

	public List<TraveluserScreensDTO> findAllUserPrivileges(Integer travelUserID,
			String userName);

	public List<SystemScreensDTO> findAllSystemScreens(Integer employeeId,
			String userName);

	public void saveEmployeeScreen(TraveluserScreensDTO employeeScreensDTO,
			String userName);

	public void deleteEmployeeScreen(TraveluserScreensDTO employeeScreensDTO,
			String userName);

	public void updateEmployeeScreen(TraveluserScreensDTO employeeScreensDTO,
			String userName);

	public SystemScreensDTO findSystemScreenByID(Integer screenId,
			String userName);

	public SystemScreens getSystemScreenByID(Integer screenId, String userName);

	public TraveluserScreens findEmployeeScreensByID(Integer Id, String userName);

	public TraveluserScreensDTO findEmployeeScreensByEmployeeID(
			Integer EmployeeId, String userName);

	public TravelUserDTO findEmployeeByStaffID(Integer employeeId, String userName);

	public SystemScreensDTO findScreenByID(Integer Id, String userName);

	public List<TravelUserDTO> listAllEmployeeUsers(String userName);
}
