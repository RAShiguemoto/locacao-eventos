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

    public List<T> listarTodos() {
        String consulta = "FROM " + entityClass.getSimpleName() + " order by id desc";
        Query query = getEntityManager().createQuery(consulta);
        return query.setMaxResults(1).getResultList();
    }

    public List<T> listarTodosAgenda() {
        String consulta = "FROM " + entityClass.getSimpleName() + " order by nome asc";
        Query query = getEntityManager().createQuery(consulta);
        return query.getResultList();
    }

    public List<T> listarTodosMigracao() {
        String consulta = "FROM " + entityClass.getSimpleName() + " order by id desc";
        Query query = getEntityManager().createQuery(consulta);
        return query.setMaxResults(1000).getResultList();
    }

    public List<T> listarTodosCompleto() {
        String consulta = "FROM " + entityClass.getSimpleName() + " order by id desc";
        Query query = getEntityManager().createQuery(consulta);
        return query.getResultList();
    }

    public List<T> listarUltimos10() {
        String consulta = "FROM " + entityClass.getSimpleName() + " order by id desc";
        Query query = getEntityManager().createQuery(consulta);
        return query.setMaxResults(10).getResultList();
    }

    public List<T> listarRapido(String campo, String prod) {
        prod = prod.toUpperCase();
        String consulta = "FROM" + entityClass.getSimpleName() + "AS i"
                + " WHERE (i." + campo + ") LIKE ('%" + prod + "%')"
                + " ORDER BY i." + campo;

        Query query = getEntityManager().createQuery(consulta);

        return query.setMaxResults(20).getResultList();

    }

    public List<T> autoComplete(String campo, String cons) {
        cons = cons.toUpperCase();
        String consulta = "FROM " + entityClass.getSimpleName() + " AS i"
                + " WHERE (i." + campo + ") LIKE ('" + cons + "%')"
                + " ORDER BY i." + campo;

        Query query = getEntityManager().createQuery(consulta);
        return query.setMaxResults(50).getResultList();
    }

    public List<T> autoCompleteAgenda(String campo, String cons) {
        cons = cons.toUpperCase();
        String consulta = "FROM " + entityClass.getSimpleName() + " AS i"
                + " WHERE (i." + campo + ") LIKE ('" + cons + "%')"
                + " ORDER BY i." + campo;

        Query query = getEntityManager().createQuery(consulta);
        return query.setMaxResults(100).getResultList();
    }

    public List<T> autoCompleteFuncionario(String campo, String cons) {
        cons = cons.toUpperCase();
        String consulta = "FROM " + entityClass.getSimpleName() + " AS i"
                + " WHERE (i.ativo is true and i." + campo + ") LIKE ('%" + cons + "%')"
                + " ORDER BY i." + campo;

        Query query = getEntityManager().createQuery(consulta);
        return query.setMaxResults(50).getResultList();
    }

}
