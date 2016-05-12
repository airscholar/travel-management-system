package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.AirlineDTO;

@Remote
public interface AirLineService {
	
	public AirlineDTO saveAirLine(AirlineDTO airlineDTO , String userName);

	public AirlineDTO updateAirLine(AirlineDTO airlineDTO , String userName);

	public AirlineDTO findAirLineById(Integer airLineId , String userName);
	public AirlineDTO findAirLineByName(String airLineName , String userName);

	public List<AirlineDTO> getAllAirLines(String userName);

    public void changeStatus(Boolean status, Integer id,
 			String userName );
    public boolean checkUniqueAirLineName(String airLineName  );

}
