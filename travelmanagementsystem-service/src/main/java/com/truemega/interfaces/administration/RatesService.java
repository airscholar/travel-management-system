package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.RatesDTO;

@Remote
public interface RatesService {

	public RatesDTO saveRates(RatesDTO RatesDTO, String userName);

	public RatesDTO updateRates(RatesDTO RatesDTO, String userName);

	public RatesDTO findRatesById(Integer RatesId, String userName);

	public List<RatesDTO> getAllRatess(String userName);

	public void changeStatus(Boolean status, Integer id, String userName);

	public List<RatesDTO> getAllRatessActive(String userName);
}
