package com.ras.controller;

import com.ras.facade.PropriedadeFacade;
import com.ras.model.Logradouro;
import com.ras.model.Propriedade;
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
public class PropriedadeController implements Serializable {
    private Propriedade propriedade;
    private List<Propriedade> propriedades = new ArrayList<>();
    private Long paramId;
    
    @Inject
    private PropriedadeFacade propriedadeFacade;
    
    public void listar() {
        propriedades = propriedadeFacade.listar();
    }
    
    public void novo() {
        if (paramId != null) {
            propriedade = propriedadeFacade.buscarPorId(paramId);
        } else {
            propriedade = new Propriedade();
            propriedade.setLogradouro(new Logradouro());
        }
    }
    
    public String salvar() {
        log.info("Salvando propriedade '{}'.", propriedade.getNome().toUpperCase());
        propriedadeFacade.salvar(propriedade);
        return "list?faces-redirect=true";
    }
    
    public String editar(Propriedade u) {
        return "form?faces-redirect=true&&id=" + u.getId();
    }
    
    public void excluir(Propriedade u) {
        log.info("Excluindo propriedade '{}'.", u.getNome());
        propriedadeFacade.excluir(u);
        listar();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensagem",  "Propriedade excluída com sucesso!"));
    }
}
