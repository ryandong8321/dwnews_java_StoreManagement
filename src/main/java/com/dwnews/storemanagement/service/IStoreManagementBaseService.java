package com.dwnews.storemanagement.service;

import java.io.Serializable;

public interface IStoreManagementBaseService <T, PK extends Serializable> {
	
	public T get(PK id);
	
	
	public T save(final T entity);
	
	
	public T update(final T entity);
	
	
	public void delete(final T entity);
	
	
	public void delete(final PK id);
	
	
}
