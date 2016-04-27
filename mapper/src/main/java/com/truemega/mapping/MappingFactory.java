package com.truemega.mapping;

import java.util.List;

import javax.ejb.Remote;

import ma.glasnost.orika.converter.ConverterFactory;

@Remote
public interface MappingFactory {

	public <T> List<T> mapAsList(List<? extends Object> objectsList,
			Class<T> targetClass);

	public void map(Object s, Object d);

	public <T> T map(Object s, Class<T> dClass);

	public ConverterFactory getConverterFactory();

}
