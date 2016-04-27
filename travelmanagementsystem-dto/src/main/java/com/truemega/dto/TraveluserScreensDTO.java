package com.truemega.dto;

import java.io.Serializable;

public class TraveluserScreensDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tmsuserId;
	private boolean addMode;
	private boolean editMode;
	private boolean deleteMode;
	private boolean viewMode;

	private TravelUserDTO tmsuser;
	private SystemScreensDTO screen;

	public TraveluserScreensDTO() {
		// TODO Auto-generated constructor stub
	}

	public boolean isAddMode() {
		return addMode;
	}

	public void setAddMode(boolean addMode) {
		this.addMode = addMode;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean isDeleteMode() {
		return deleteMode;
	}

	public void setDeleteMode(boolean deleteMode) {
		this.deleteMode = deleteMode;
	}

	public Integer getId() {
		return tmsuserId;
	}

	public void setId(Integer tmsuserId) {
		this.tmsuserId = tmsuserId;
	}

	public TravelUserDTO getEmployee() {
		return tmsuser;
	}

	public void setEmployee(TravelUserDTO employee) {
		this.tmsuser = employee;
	}

	public SystemScreensDTO getScreen() {
		return screen;
	}

	public void setScreen(SystemScreensDTO screen) {
		this.screen = screen;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}

}