package com.truemega.interfaces.administration;

import java.util.List;

import javax.ejb.Remote;

import com.truemega.dto.RoomTypeDTO;

@Remote
public interface RoomTypeService {

	public RoomTypeDTO saveRoomType(RoomTypeDTO roomTypeDTO, String userName);

	public RoomTypeDTO updateRoomType(RoomTypeDTO roomTypeDTO, String userName);

	public RoomTypeDTO findRoomByName(String roomName, String userName);

	public RoomTypeDTO findRoomTypeById(Integer roomTypeId, String userName);

	public List<RoomTypeDTO> getAllRoomTypes(String userName);

	public void changeStatus(Boolean status, Integer id, String userName);

	public boolean checkUniqueRoomTypeName(String roomTypeName);
	public List<RoomTypeDTO> getAllRoomTypeActive(String userName);
}
