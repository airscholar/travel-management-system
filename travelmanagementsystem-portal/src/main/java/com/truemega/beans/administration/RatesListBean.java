package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.AirlineDTO;
import com.truemega.dto.RatesDTO;
import com.truemega.interfaces.administration.RatesService;

@ManagedBean(name = "rateslist")
@ViewScoped
public class RatesListBean extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RatesService ratesService;
	private List<RatesDTO> ratesDTOs;

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return "administration/rateslist.xhtml";
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@PostConstruct
	@Override
	public void load() {
		// TODO Auto-generated method stub
		try {
			ratesDTOs = ratesService.getAllRatess(getUserName());
			System.out.println("Ratess ======  " + ratesDTOs.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
    
	public void changeStatus(AjaxBehaviorEvent event) {

		try {
			RatesDTO ratesDTO = (RatesDTO) event.getComponent()
					.getAttributes().get("ratemodel");

			ratesService.changeStatus(ratesDTO.getStatus(), ratesDTO.getId(),
					getUserName());

			load();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param args
	 */



	public List<RatesDTO> getRatesDTOs() {
		return ratesDTOs;
	}

	public void setRatesDTOs(List<RatesDTO> RatesDTOs) {
		this.ratesDTOs = RatesDTOs;
	}

}
