package com.qs.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.qs.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> entityClass;

	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;

	public BaseDaoImpl() {
		Class<T> actualclass = (Class<T>) getClass();
		ParameterizedType type = (ParameterizedType) actualclass
				.getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
	}

	public void saveOrUpdate(T entity) {
		if (beforeSave(entity)) {
			this.hibernateTemplate.saveOrUpdate(entity);

		}
		afterSave(entity);
	}

	public void merge(T entity) {

		this.hibernateTemplate.merge(entity);
	}

	public T getById(Serializable id) {
		return (T) this.hibernateTemplate.get(entityClass, id);
	}

	public void delete(T entity) {
		this.hibernateTemplate.delete(entity);
	}

	public void delAll(List<T> entities) {
		this.hibernateTemplate.deleteAll(entities);
	}

	public List<T> listAll() {
		return this.hibernateTemplate.find("From " + entityClass.getName());
	}

	public List<T> listByCondition(String hql) {
		return this.hibernateTemplate.find(hql);
	}

	public List<T> getByProperty(String propertyName, Object propertyValue) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq(propertyName, propertyValue));
		List<T> entities = this.hibernateTemplate.findByCriteria(criteria);
		return entities;
	}

	public List<T> getByPropertys(Map<String, Object> params) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		if (params != null) {
			for (Map.Entry<String, ?> entry : params.entrySet()) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}

		List<T> entities = this.hibernateTemplate.findByCriteria(criteria);
		return entities;
	}

	protected boolean beforeSave(T entity) {
		return true;
	}

	protected void afterSave(T entity) {
	}

	/**
	 * Construct a DetachedCriteria object according to an example object.
	 * 
	 * @param entity
	 *            The example object.
	 * @return A DetachedCriteria object.
	 */
	protected DetachedCriteria constructDetachedCriteria(T entity) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity));
		return detachedCriteria;
	}

	/**
	 * firstResult 设置满足条件的第1条记录的位置 maxResults 限定查询返回的记录总数 即每页显示多少条记录
	 */
	public List<T> listByConditionPage(final String hql, final int firstResult,
			final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			public List<T> doInHibernate(Session session) // 此处的session可以不用管，自动获得

					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				// query.setCacheable(true); //使用缓存
				return query.list();
			}
		});
	}

	/**
	 * 实现编号的自动增长（两位地区编码+三位自增数据）
	 * 
	 * @param str
	 * @param tables
	 * @param areaFront
	 * @return
	 * @createDate 2014-1-10
	 */
	public String getMaxNum(final String str, final String tables,
			final String areaFront) {
		String max = (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "select max(" + str + ") from " + tables
								+ " as w where substring(" + str + ",1,2)='"
								+ areaFront + "'";
						Query q = session.createQuery(hql);
						List all = q.list();
						if (all != null && all.size() > 0) {
							return all.get(0);
						}
						return null;
					}
				});
		return max;
	}

	/**
	 * 查询所有记录数 　　
	 * 
	 * @return 总记录数 　　
	 */
	public int getAllRowCount(String hql) {
		int count = Integer.parseInt(getHibernateTemplate().find(hql).get(0)
				.toString());
		return count;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
