package com.truemega.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author hp
 */
@Entity
@Table(name = "USERSCREENS")
public class TraveluserScreens implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "traveluser_screens_seq")
	@SequenceGenerator(name = "traveluser_screens_seq", sequenceName = "TRAVELUSER_SCREENS_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ADDMODE")
	private boolean addMode;
	@Column(name = "EDITMODE")
	private boolean editMode;
	@Column(name = "DELETEMODE")
	private boolean deleteMode;
	@Column(name = "VIEWMODE")
	private boolean viewMode;
	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
	private TravelUser employee;

	@ManyToOne
	@JoinColumn(name = "SCREEN_ID", referencedColumnName = "SCREEN_ID")
	private SystemScreens screen;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}

	public TravelUser getEmployee() {
		return employee;
	}

	public void setEmployee(TravelUser employee) {
		this.employee = employee;
	}

	public SystemScreens getScreen() {
		return screen;
	}

	public void setScreen(SystemScreens screen) {
		this.screen = screen;
	}

}
