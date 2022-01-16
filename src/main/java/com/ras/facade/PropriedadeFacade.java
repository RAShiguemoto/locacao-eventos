package com.ras.facade;

import com.ras.model.Propriedade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PropriedadeFacade extends AbstractFacade<Propriedade> {

    @PersistenceContext(unitName = "locacaoPU")
    private EntityManager em;
    
    public List<Propriedade> listarPropriedadesAsc() {
        Query query = em.createQuery("FROM Propriedade AS u WHERE u.ativo IS TRUE ORDER BY u.login ASC");
        return query.getResultList();
    }
    
    public Propriedade buscarPorId(Long id) {
        Query query = em.createQuery("FROM Propriedade AS u WHERE u.id = :id");
        query.setParameter("id", id);

        return (Propriedade) query.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropriedadeFacade() {
        super(Propriedade.class);
    }
}
