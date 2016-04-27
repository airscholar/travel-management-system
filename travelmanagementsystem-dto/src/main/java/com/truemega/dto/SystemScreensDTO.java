package com.truemega.dto;

import java.io.Serializable;

public class SystemScreensDTO implements Serializable {
    private static final long serialVersionUID = 1L;
  
    private Integer screenId;
    private String screenName;
    private String screenURL; 
    private CategoryDTO category;
    private int screenOrder;
    private String openedScreenCSSClass;
    
    public SystemScreensDTO() {
    }
    

    public String getScreenURL() {
		return screenURL;
	}


	public void setScreenURL(String screenURL) {
		this.screenURL = screenURL;
	}


	public int getScreenOrder() {
		return screenOrder;
	}

	public void setScreenOrder(int screenOrder) {
		this.screenOrder = screenOrder;
	}
	

	public CategoryDTO getCategory() {
		return category;
	}


	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }


	public String getOpenedScreenCSSClass() {
		return openedScreenCSSClass;
	}


	public void setOpenedScreenCSSClass(String openedScreenCSSClass) {
		this.openedScreenCSSClass = openedScreenCSSClass;
	}

}