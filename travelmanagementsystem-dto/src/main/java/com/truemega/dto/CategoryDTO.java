package com.truemega.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer categoryId;
	private String categoryName;
	private Integer categoryOrder;

	private List<TraveluserScreensDTO> tmsuserScreens;

	private boolean flage;
	private int screenSize;

	private String expandedCSSClass = "false";
	private String collapse = "collapse";
	private String linkCSS = "collapsed";

	public String getLinkCSS() {
		return expandedCSSClass.equalsIgnoreCase("false") ? linkCSS : "";
	}

	public void setLinkCSS(String linkCSS) {
		this.linkCSS = linkCSS;
	}

	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public boolean isFlage() {
		return flage;
	}

	public void setFlage(boolean flage) {
		this.flage = flage;
	}

	public List<TraveluserScreensDTO> getEmployeeScreens() {
		return tmsuserScreens;
	}

	public void setEmployeeScreens(List<TraveluserScreensDTO> tmsuserScreens) {
		this.tmsuserScreens = tmsuserScreens;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public String getExpandedCSSClass() {
		return expandedCSSClass;
	}

	public void setExpandedCSSClass(String expandedCSSClass) {
		this.expandedCSSClass = expandedCSSClass;
	}

	public String getCollapse() {
		return collapse;
	}

	public void setCollapse(String collapse) {
		this.collapse = collapse;
	}

	public List<TraveluserScreensDTO> getTmsuserScreens() {
		return tmsuserScreens;
	}

	public void setTmsuserScreens(List<TraveluserScreensDTO> tmsuserScreens) {
		this.tmsuserScreens = tmsuserScreens;
	}

	public void setCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}

	
	
}
