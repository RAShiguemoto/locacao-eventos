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
    private List<Usuario> usuarios = new ArrayList<>();
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    public void listar() {
        usuarios = usuarioFacade.listar();
    }
}
