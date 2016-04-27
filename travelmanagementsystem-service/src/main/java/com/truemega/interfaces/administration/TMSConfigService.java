package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.ConfigDTO;
import com.truemega.dto.TravelUserDTO;

@Remote
public interface TMSConfigService {

	public List<ConfigDTO> findAllConfiguration(String userName);

	public ConfigDTO findConfigurationByName(String configName, String userName);

	public TravelUserDTO getAdministrator(String userName);

}
