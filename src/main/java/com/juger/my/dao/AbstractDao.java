package com.juger.my.dao;

import javax.persistence.EntityManager;

public abstract class AbstractDao<T> {
	
	
	private Class<T> entityClass;

	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
        getEntityManager().persist(entity);
	}

    public T read(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}
