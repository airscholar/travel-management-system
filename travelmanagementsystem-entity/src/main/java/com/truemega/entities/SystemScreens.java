/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author lenovo1
 */
@Entity
@Table(name = "SYSTEM_SCREENS")
public class SystemScreens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SCREEN_ID")
    private Integer screenId;
    @Column(name = "SCREEN_NAME")
    private String screenName;        
    @Column(name = "SCREEN_URL")
    private String screenURL;
    @ManyToOne
	@JoinColumn(name="CATEGORY_ID")
    private Category category;    
    @Column(name = "SCREEN_ORDER")
    private int screenOrder;
    
    
    public SystemScreens() {
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
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
    
}
