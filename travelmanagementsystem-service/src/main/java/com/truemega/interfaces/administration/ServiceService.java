package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.ServiceTypeDTO;

@Remote
public interface ServiceService {
	


	public ServiceTypeDTO findServiceById(Integer serviceId , String userName);

	public List<ServiceTypeDTO> getAllServices(String userName);
			


}
