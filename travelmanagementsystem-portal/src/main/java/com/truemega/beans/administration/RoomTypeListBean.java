package com.truemega.beans.administration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.truemega.beans.TravelListBean;
import com.truemega.dto.RoomTypeDTO;
import com.truemega.interfaces.administration.RoomTypeService;


@ManagedBean(name = "roomtypelist")
@ViewScoped
public class RoomTypeListBean  extends TravelListBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	private RoomTypeService roomTypeService ;
	
	 private List<RoomTypeDTO> roomTypeDTOs ;

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return "administration/roomtypelist.xhtml";
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
			roomTypeDTOs = roomTypeService.getAllRoomTypes(getUserName());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
		}
	
		
	}
    
	public void changeStatus(AjaxBehaviorEvent event) {

		try {
			RoomTypeDTO roomTypeDTO = (RoomTypeDTO) event.getComponent()
					.getAttributes().get("roomtypemodel");

			roomTypeService.changeStatus(roomTypeDTO.getStatus(), roomTypeDTO.getId(),
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

	public List<RoomTypeDTO> getRoomTypeDTOs() {
		return roomTypeDTOs;
	}

	public void setRoomTypeDTOs(List<RoomTypeDTO> roomTypeDTOs) {
		this.roomTypeDTOs = roomTypeDTOs;
	}



}
