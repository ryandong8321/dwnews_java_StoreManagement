package com.dwnews.storemanagement.service;

import java.io.Serializable;
import com.dwnews.storemanagement.dao.StoreManagementBaseDAOImpl;

public abstract class StoreManagementBaseServiceImpl<T, PK extends Serializable, K extends StoreManagementBaseDAOImpl<T,PK>> implements IStoreManagementBaseService<T, PK> {
	
	/**
	 * 取得当前DAO
	 */
	protected abstract K getCurrentDAO();
	
	
	public T get(PK id) {
		return (T) getCurrentDAO().get(id);
	}
	
	
	public T save(final T entity) {
		return (T) getCurrentDAO().save(entity);
	}
	
	
	public T update(final T entity) {
		return (T) getCurrentDAO().update(entity);
	}
	
	
	public void delete(final T entity) {
		getCurrentDAO().delete(entity);
	}
	
	
	public void delete(final PK id) {
		getCurrentDAO().delete(id);
	}
	
	
}