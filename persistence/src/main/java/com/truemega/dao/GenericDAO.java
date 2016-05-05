package com.truemega.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface GenericDAO {

	<T> T findEntityById(Class<T> entityClass, Serializable id);

	<T> T findEntityByNamedQuery(String namedQueryName);

	<T> T findEntityByNamedQuery(String namedQueryName,
			Map<String, Object> params);

	<T> List<T> findListByNamedQuery(String namedQueryName);

	<T> List<T> findListByNamedQuery(String namedQueryName,
			Map<String, Object> params);

	<T> List<T> findListByNamedQuery(String namedQueryName, int start,
			int maxSize);

	<T> List<T> findListByNamedQuery(String namedQueryName,
			Map<String, Object> params, int start, int maxSize);

	<T> T findEntityByQuery(String namedQueryName);

	<T> T findEntityByQuery(String namedQueryName, Map<String, Object> params);

	<T> List<T> findListByQuery(String namedQueryName);

	<T> List<T> findListByQuery(String namedQueryName,
			Map<String, Object> params);

	<T> List<T> findListByQuery(String namedQueryName, int start, int maxSize);

	<T> List<T> findListByQuery(String namedQueryName,
			Map<String, Object> params, int start, int maxSize);

	<T> T saveEntity(T entity);

	<T> T updateEntity(T entity);

	<T> void deleteEntity(T entity);

	void executeDeleteQuery(String query);

	<T> List<T> executeNativeQuery(String sqlQuery);

	<T> List<T> executeJPQLQuery(String jpqlQuery);

	public <T> List<T> executeDynamicQuery(String dynamicQuery, Class<T> klass,
			boolean DML);

	public <T> void updateWithParamaters(String QueryName,
			Map<String, Object> params);

	public <T> List<T> findAll(Class<T> klass);

	public int executeDeleteNativeQuery(String sqlQuery);

	public void flush();

	public int executeInsertNativeQuery(String sqlQuery);

	public void callStoredProcedure(String procedureName);
}
