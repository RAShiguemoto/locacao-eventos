package com.ras.controller;

import com.ras.facade.UsuarioFacade;
import com.ras.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author raula
 */

@Named
@ViewScoped
@Data
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
        usuario.setAtivo(Boolean.TRUE);
        usuarioFacade.salvar(usuario);
        return "list?faces-redirect=true";
    }
    
    public String editar(Usuario u) {
        return "form?faces-redirect=true&&id=" + u.getId();
    }
    
    public void excluir(Usuario u) {
        usuarioFacade.excluir(u);
        listar();
    }
}
