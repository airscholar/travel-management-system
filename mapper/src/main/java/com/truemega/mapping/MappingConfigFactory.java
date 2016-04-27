package com.truemega.mapping;

import java.util.HashMap;
import java.util.Map;

public enum MappingConfigFactory {
	INSTANCE;

	private Map<String, MappingConfig> mappingConfigurations;

	private MappingConfigFactory() {
		mappingConfigurations = new HashMap<String, MappingConfig>();
	}

	public Map<String, MappingConfig> getMappingConfigurations() {

		return mappingConfigurations;
	}

}
