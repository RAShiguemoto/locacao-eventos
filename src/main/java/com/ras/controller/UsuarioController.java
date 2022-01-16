package com.ras.controller;

import com.ras.facade.UsuarioFacade;
import com.ras.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author raula
 */

@Named
@ViewScoped
@Data
@Log4j2
public class UsuarioController implements Serializable {
    private Usuario usuario;
    private List<Usuario> usuarios = new ArrayList<>();
    private Long paramId;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    public void listar() {
        usuarios = usuarioFacade.listar();
    }
    
    public void novo() {
        if (paramId != null) {
            usuario = usuarioFacade.buscarPorId(paramId);
        } else {
            usuario = new Usuario();
        }
    }
    
    public String salvar() {
        log.info("Salvando usuário '{}'.", usuario.getLogin());
        usuario.setAtivo(Boolean.TRUE);
        usuarioFacade.salvar(usuario);
        return "list?faces-redirect=true";
    }
    
    public String editar(Usuario u) {
        return "form?faces-redirect=true&&id=" + u.getId();
    }
    
    public void excluir(Usuario u) {
        log.info("Excluindo usuário '{}'.", u.getLogin());
        usuarioFacade.excluir(u);
        listar();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensagem",  "Usuário excluído com sucesso!"));
    }
}
