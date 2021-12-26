package com.ras.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void inserir(T entity) {
        getEntityManager().persist(entity);
    }

    public void salvar(T entity) {
        getEntityManager().merge(entity);
    }

    public void excluir(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T pesquisar(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> listar() {
        String consulta = "FROM " + entityClass.getSimpleName() + " ORDER BY id DESC";
        Query query = getEntityManager().createQuery(consulta);
        return query.getResultList();
    }
    
    public List<T> autoComplete(String atributo, String cons) {
        String consulta = "FROM " + entityClass.getSimpleName() 
                + " WHERE UPPER(" + atributo + ") LIKE UPPER('" + cons + "%')"
                + " ORDER BY " + atributo + " ASC";
        
        Query query = getEntityManager().createQuery(consulta);
        return query.getResultList();
    }
}
