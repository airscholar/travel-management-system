package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.MaxMinValuesDTO;

@Remote
public interface MaxMinService {
	
	public MaxMinValuesDTO saveMaxMinValues(MaxMinValuesDTO MaxMinValuesDTO , String userName);

	public MaxMinValuesDTO updateMaxMinValues(MaxMinValuesDTO MaxMinValuesDTO , String userName);

	public MaxMinValuesDTO findMaxMinValuesById(Integer MaxMinValuesId , String userName);
	public MaxMinValuesDTO findMaxMinValuesByName(String MaxMinValuesName , String userName);

	public List<MaxMinValuesDTO> getAllMaxMinValuess(String userName);

    public void changeStatus(Boolean status, Integer id,
 			String userName );
    public boolean checkUniqueMaxMinValuesName(String MaxMinValuesName  );
    public List<MaxMinValuesDTO> getAllMaxMinValuesActive(String userName);

}
