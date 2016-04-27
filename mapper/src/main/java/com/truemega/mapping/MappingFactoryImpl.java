package com.truemega.mapping;

import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import com.truemega.logger.LoggerService;

@Stateless
public class MappingFactoryImpl implements MappingFactory {

	private MapperFactory factory = new DefaultMapperFactory.Builder().build();
	private MappingConfigFactory configFactory = MappingConfigFactory.INSTANCE;
	private LoggerService loggerService = new LoggerService();

	@PostConstruct
	public void init() {

		load();
	}

	private void load() {

		try {
			loggerService.logMapperInfo("Begin Loading Mapper Configuration");
			for (Entry<String, MappingConfig> entry : configFactory
					.getMappingConfigurations().entrySet()) {
				MappingConfig mappingConfig = entry.getValue();
				ClassMapBuilder<?, ?> classMapBuilder = factory.classMap(
						mappingConfig.getSourceClass(),
						mappingConfig.getTargetClass());
				for (Entry<String, String> field : mappingConfig.getFields()
						.entrySet()) {
					classMapBuilder = classMapBuilder.field(field.getKey(),
							field.getValue());
				}
				classMapBuilder = classMapBuilder.byDefault();
				classMapBuilder.register();

			}
			loggerService
					.logMapperInfo("Mapper Configuration loaded successfully");
			loggerService.logMapperInfo("Registering Converters");
			registerConverters();

		} catch (Exception e) {
			loggerService.logMapperError("Couldn't Load Mapper configuration",
					e);
		}

	}

	public <T> List<T> mapAsList(List<? extends Object> objectsList,
			Class<T> targetClass) {
		List<T> t = null;
		try {
			t = factory.getMapperFacade().mapAsList(objectsList, targetClass);
		} catch (Exception e) {
			loggerService.logMapperError("Coludn't Map List into "
					+ targetClass.getName(), e);
		}
		return t;
	}

	public void map(Object s, Object d) {
		try {
			factory.getMapperFacade().map(s, d);
		} catch (Exception e) {
			loggerService.logMapperError("Couldn't Map Object " + s
					+ " to Object " + d, e);
		}
	}

	public <T> T map(Object s, Class<T> dClass) {
		T result = null;
		try {
			result = factory.getMapperFacade().map(s, dClass);
		} catch (Exception e) {
			loggerService.logMapperError("Couldn't Map Object " + s
					+ " to class type " + dClass.getName(), e);
		}
		return result;
	}

	private void registerConverters() {
		ConverterFactory converterFactory = factory.getConverterFactory();

	}

	public ConverterFactory getConverterFactory() {
		return factory.getConverterFactory();

	}

}
