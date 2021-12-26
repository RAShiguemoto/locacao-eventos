package com.ras.facade;

import com.ras.model.Cidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CidadeFacade extends AbstractFacade<Cidade> {

    @PersistenceContext(unitName = "locacaoPU")
    private EntityManager em;
        
    public List<Cidade> listarCidadesAsc() {
        Query query = em.createQuery("FROM Cidade AS c WHERE c.ativo IS TRUE ORDER BY c.nome ASC");
        return query.getResultList();
    }
    
    public Cidade buscarPorId(Long id) {
        Query query = em.createQuery("FROM Cidade AS c WHERE c.id = :id");
        query.setParameter("id", id);

        return (Cidade) query.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CidadeFacade() {
        super(Cidade.class);
    }
}
