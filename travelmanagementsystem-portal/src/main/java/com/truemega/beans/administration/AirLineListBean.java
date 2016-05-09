package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.AirlineDTO;
import com.truemega.interfaces.administration.AirLineService;


@ManagedBean(name = "airlinelist")
@ViewScoped
public class AirLineListBean extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	private AirLineService airLineService ;
	 private List<AirlineDTO> airlineDTOs ;

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return "administration/airlinelist.xhtml";
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
			airlineDTOs = airLineService.getAllAirLines(getUserName());
			System.out.println("airlineDTOs ======  " + airlineDTOs.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}
	}
    
	public void changeStatus(AjaxBehaviorEvent event) {

		try {
			AirlineDTO airlineDTO = (AirlineDTO) event.getComponent()
					.getAttributes().get("airlinemodel");

			airLineService.changeStatus(airlineDTO.getStatus(), airlineDTO.getId(),
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
		return false;
	}

	public List<AirlineDTO> getAirlineDTOs() {
		return airlineDTOs;
	}

	public void setAirlineDTOs(List<AirlineDTO> airlineDTOs) {
		this.airlineDTOs = airlineDTOs;
	}

}
