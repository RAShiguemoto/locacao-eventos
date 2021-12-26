package com.ras.controller;

import com.ras.converter.GenericConverter;
import com.ras.facade.CidadeFacade;
import com.ras.model.Cidade;
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
public class CidadeController implements Serializable {
    private Cidade cidade;
    private List<Cidade> cidades = new ArrayList<>();
    private Long paramId;
    
    @Inject
    private CidadeFacade cidadeFacade;
    
    private GenericConverter converter;

    public GenericConverter converter() {
        if (converter == null) {
            converter = new GenericConverter(cidadeFacade);
        }
        return converter;
    }
    
    public void listar() {
        cidades = cidadeFacade.listar();
    }
    
    public void novo() {
        if (paramId != null) {
            cidade = cidadeFacade.buscarPorId(paramId);
        } else {
            cidade = new Cidade();
        }
    }
    
    public String salvar() {
        cidadeFacade.salvar(cidade);
        return "list?faces-redirect=true";
    }
    
    public String editar(Cidade u) {
        return "form?faces-redirect=true&&id=" + u.getId();
    }
    
    public void excluir(Cidade u) {
        cidadeFacade.excluir(u);
        listar();
    }
    
    public List<Cidade> autoComplete(String cons) {
        return cidadeFacade.autoComplete("nome", cons);
    }
}
