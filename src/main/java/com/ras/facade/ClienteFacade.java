package com.ras.facade;

import com.ras.model.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "locacaoPU")
    private EntityManager em;
        
    public List<Cliente> listarClientesAsc() {
        Query query = em.createQuery("FROM Cliente AS c WHERE c.ativo IS TRUE ORDER BY c.nome ASC");
        return query.getResultList();
    }
    
    public Cliente buscarPorId(Long id) {
        Query query = em.createQuery("FROM Cliente AS c WHERE c.id = :id");
        query.setParameter("id", id);

        return (Cliente) query.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
}
