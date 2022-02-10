package com.ras.facade;

import com.ras.model.Agendamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AgendamentoFacade extends AbstractFacade<Agendamento> {

    @PersistenceContext(unitName = "locacaoPU")
    private EntityManager em;
            
    public Agendamento buscarPorId(Long id) {
        Query query = em.createQuery("FROM Agendamento AS a WHERE a.id = :id");
        query.setParameter("id", id);

        return (Agendamento) query.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgendamentoFacade() {
        super(Agendamento.class);
    }
}
