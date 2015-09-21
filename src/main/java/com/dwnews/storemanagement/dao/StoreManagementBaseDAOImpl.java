package com.dwnews.storemanagement.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import com.dwnews.storemanagement.util.ReflectionUtils;

@SuppressWarnings("unchecked")
public class StoreManagementBaseDAOImpl<T, PK extends Serializable> implements IStoreManagementBaseDAO<T, PK> {

	protected static Logger logger = LoggerFactory.getLogger(StoreManagementBaseDAOImpl.class);

	protected Class<T> clazz;

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public StoreManagementBaseDAOImpl() {
		this.clazz = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T get(PK id) {
		return (T) getSession().get(clazz, id);
	}

	public T save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
		return entity;
	}

	public T update(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.debug("update entity: {}", entity);
		return entity;
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id不能为空");
		getSession().delete(id);
		logger.debug("delete entity {},id is {}", clazz.getSimpleName(), id);
	}

	public <X> X findUnique(String hql, Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	public <X> X findUnique(String hql, Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	public <X> List<X> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	public <X> List<X> find(String hql, Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	public int batchExecute(String hql, Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	public int batchExecute(String hql, Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	public Pagination<T> findPage(Pagination<T> page, String hql, Object... values) {
		Assert.notNull(page, "page不能为空");
		Query query = this.createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = (Long) findUnique(getSimpleCountHQL(hql), values);
			page.setTotal(totalCount);
		}

		query.setFirstResult(page.getFirst2());
		query.setMaxResults(page.getPageSize());

		List<T> list = query.list();

		page.setRows(list);
		return page;
	}

	public Pagination<T> findPage(Pagination<T> page, String hql, Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");
		Query query = this.createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = (Long) findUnique(getSimpleCountHQL(hql), values);
			page.setTotal(totalCount);
		}

		query.setFirstResult(page.getFirst2());
		query.setMaxResults(page.getPageSize());

		List<T> list = query.list();

		page.setRows(list);
		return page;
	}

	private String getSimpleCountHQL(String hql) {

		String fromHql = hql;

		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}

}
