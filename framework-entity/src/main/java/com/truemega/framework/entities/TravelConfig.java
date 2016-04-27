/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truemega.framework.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CONFIG")

public class TravelConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "CONFIG_NAME")
    private String configName;
    
    @Column(name = "CONFIG_VALUE")
    private String configValue;
    
    @Id
    @Basic(optional = false)
    @Column(name = "CONFIG_ID")
    private Integer configId;

    public TravelConfig() {
    }

    public TravelConfig(Integer configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }
    
}
