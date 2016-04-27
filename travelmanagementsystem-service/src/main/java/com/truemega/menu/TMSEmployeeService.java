package com.truemega.menu;

import javax.ejb.Remote;

import com.truemega.dto.TravelUserDTO;

@Remote
public interface TMSEmployeeService {
	public TravelUserDTO findUserByUserName(String username, String caller);

	public Boolean authenticateUser(String username, String password);
}
