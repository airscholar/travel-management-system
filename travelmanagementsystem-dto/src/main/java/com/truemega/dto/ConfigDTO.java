package com.truemega.dto;

import java.io.Serializable;

public class ConfigDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer configId;

	private String configName;

	private String configValue;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
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

}
