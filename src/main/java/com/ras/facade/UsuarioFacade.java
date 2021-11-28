package com.ras.facade;

import com.ras.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "locacaoPU")
    private EntityManager em;
    
    public Usuario autenticarUsuario(String login, String senha) {
        try {
            Query query = em.createQuery("FROM Usuario AS u WHERE u.login = :login AND u.senha = :senha");
            
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            
            return (Usuario) query.getSingleResult();
            
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Usuario> listarUsuariosAsc() {
        Query query = em.createQuery("FROM Usuario AS u WHERE u.ativo IS TRUE ORDER BY u.login ASC");
        return query.getResultList();
    }
    
    public Usuario buscarPorId(Long id) {
        Query query = em.createQuery("FROM Usuario AS u WHERE u.id = :id");
        query.setParameter("id", id);

        return (Usuario) query.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
}
