package com.truemega.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GenericDaoImpl implements GenericDAO {
	// Set Persistence Unit Name
	@PersistenceContext(unitName = "TMS")
	private EntityManager em;

	@Override
	public <T> T findEntityById(Class<T> entityClass, Serializable id) {
		return em.find(entityClass, id);
	}

	@Override
	public <T> T findEntityByNamedQuery(String namedQueryName) {
		return findEntityByNamedQuery(namedQueryName, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findEntityByNamedQuery(String namedQueryName,
			Map<String, Object> params) {
		Query query = em.createNamedQuery(namedQueryName);

		if (params != null) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		if (query.getResultList() != null && !query.getResultList().isEmpty()) {
			return (T) query.getResultList().get(0);
		} else {
			return null;
		}

	}

	@Override
	public <T> List<T> findListByNamedQuery(String namedQueryName) {
		return findListByNamedQuery(namedQueryName, null, -1, -1);
	}

	@Override
	public <T> List<T> findListByNamedQuery(String namedQueryName,
			Map<String, Object> params) {
		return findListByNamedQuery(namedQueryName, params, -1, -1);
	}

	@Override
	public <T> List<T> findListByNamedQuery(String namedQueryName, int start,
			int maxSize) {
		return findListByNamedQuery(namedQueryName, null, start, maxSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByNamedQuery(String namedQueryName,
			Map<String, Object> params, int start, int maxSize) {
		Query query = em.createNamedQuery(namedQueryName);
		if (start > -1) {
			query.setFirstResult(start);
		}
		if (maxSize > -1) {
			query.setMaxResults(maxSize);
		}

		if (params != null) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		return query.getResultList();
	}

	@Override
	public <T> T findEntityByQuery(String QueryName) {
		return findEntityByQuery(QueryName, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findEntityByQuery(String QueryName, Map<String, Object> params) {
		Query query = em.createQuery(QueryName);

		if (params != null) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		if (query.getResultList() != null && !query.getResultList().isEmpty()) {
			return (T) query.getResultList().get(0);
		} else {
			return null;
		}

	}

	@Override
	public <T> List<T> findListByQuery(String QueryName) {
		return findListByQuery(QueryName, null, -1, -1);
	}

	@Override
	public <T> List<T> findListByQuery(String QueryName,
			Map<String, Object> params) {
		return findListByQuery(QueryName, params, -1, -1);
	}

	@Override
	public <T> List<T> findListByQuery(String QueryName, int start, int maxSize) {
		return findListByQuery(QueryName, null, start, maxSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByQuery(String QueryName,
			Map<String, Object> params, int start, int maxSize) {
		Query query = em.createQuery(QueryName);
		if (start > -1) {
			query.setFirstResult(start);
		}
		if (maxSize > -1) {
			query.setMaxResults(maxSize);
		}

		if (params != null) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		return query.getResultList();
	}

	@Override
	public <T> void updateWithParamaters(String QueryName,
			Map<String, Object> params) {
		Query query = em.createQuery(QueryName);
		if (params != null) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		query.executeUpdate();
	}

	@Override
	public <T> T saveEntity(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public <T> T updateEntity(T entity) {
		try {
			T e = em.merge(entity);
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> void deleteEntity(T entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void executeDeleteQuery(String jpqlQuery) {
		Query query = em.createQuery(jpqlQuery);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executeNativeQuery(String jpqlQuery) {
		try {
			return em.createNativeQuery(jpqlQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executeJPQLQuery(String jpqlQuery) {
		return em.createQuery(jpqlQuery).getResultList();
	}

	@Override
	public <T> List<T> executeDynamicQuery(String dynamicQuery, Class<T> klass,
			boolean DML) {
		Query query = null;
		try {
			query = em.createQuery(dynamicQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (klass != null) {
			if (!DML)
				return ((List<T>) query.getResultList());
			else {
				query.executeUpdate();
			}
			return null;
		} else {
			if (!DML)
				return query.getResultList();
			else {
				query.executeUpdate();
				return null;
			}
		}
	}

	@Override
	public <T> List<T> findAll(Class<T> klass) {
		Query query = null;
		try {
			query = em.createQuery("SELECT model FROM " + klass.getSimpleName()
					+ " model");
			List result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int executeDeleteNativeQuery(String sqlQuery) {
		// TODO Auto-generated method stub

		return em.createNativeQuery(sqlQuery).executeUpdate();

	}

	@Override
	public void flush() {

		em.flush();
	}

	@Override
	public int executeInsertNativeQuery(String sqlQuery) {
		// TODO Auto-generated method stub
		return em.createNativeQuery(sqlQuery).executeUpdate();
	}

	@Override
	public void callStoredProcedure(String procedureName) {
		// TODO Auto-generated method stub

		em.createNativeQuery("call "+procedureName+"()").executeUpdate();

	}
}
