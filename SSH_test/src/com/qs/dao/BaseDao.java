package com.qs.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The BaseDao interface should be implemented by BaseDaoImpl and extended by
 * all the other interfaces whose duty is to operate data. It defines general
 * methods of operating data.
 * 
 * @author Vinson.Liu
 * 
 * @param <T>
 *            The entity to be operated.
 */
public interface BaseDao<T> {

	/**
	 * Save or update an entity. If the entity is transient, it will be saved.
	 * Or it will be updated.
	 * 
	 * @param entity
	 *            The entity to be saved or updated.
	 */
	void saveOrUpdate(T entity);
	
	

	void merge(T entity);
	/**
	 * Search an entity by it's id.
	 * 
	 * @param id
	 *            The id of entity.
	 * @return The entity searched out or null if no entity be searched out.
	 */
	T getById(Serializable id);

	/**
	 * Delete an entity
	 * 
	 * @param entity
	 *            The entity to be deleted.
	 */
	void delete(T entity);

	void delAll(List<T> entities);

	/**
	 * List all the objects whose type entity of T.
	 * 
	 * @return The list of objects whose type is entity of T.
	 */
	List<T> listAll();

	List<T> listByCondition(String hql);

	List<T> getByProperty(String propertyName, Object propertyValue);

	List<T> listByConditionPage(final String hql,final int firstResult,final int maxResults);
	
	String getMaxNum(final String str, final String tables,
			final String areaFront);

	int getAllRowCount(String hql);
}
