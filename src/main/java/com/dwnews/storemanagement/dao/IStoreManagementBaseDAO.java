package com.dwnews.storemanagement.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IStoreManagementBaseDAO<T, PK extends Serializable> {
	
	public T get(PK id);
	
	
	/**
	 * 保存新增或修改的对象.
	 */
	public T save(final T entity);
	
	
	/**
	 * 保存新增或修改的对象.
	 */
	public T update(final T entity);
	
	
	/**
	 * 删除对象.
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity);

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id);
	
	
	/**
	 * 按HQL查询唯一对象.
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public <X> X findUnique(final String hql, final Object... values);
	
	public <X> X findUnique(final String hql, final Map<String, ?> values);
	

	/**
	 * 按HQL查询对象列表.
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public <X> List<X> find(final String hql, final Object... values);
	
	public <X> List<X> find(final String hql, final Map<String, ?> values);
	
	
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Object... values);
	
	public int batchExecute(final String hql, final Map<String, ?> values);
	
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	public Pagination<T> findPage(final Pagination<T> page, final String hql, final Object... values);
	
	public Pagination<T> findPage(final Pagination<T> page, final String hql, final Map<String, ?> values);
	
}
