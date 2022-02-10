package com.ras.controller;

import com.ras.converter.GenericConverter;
import com.ras.facade.ClienteFacade;
import com.ras.model.Cliente;
import com.ras.model.Contato;
import com.ras.model.Logradouro;
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
public class ClienteController implements Serializable {
    private Cliente cliente;
    private List<Cliente> clientes = new ArrayList<>();
    private Long paramId;
    
    @Inject
    private ClienteFacade clienteFacade;
    
    private GenericConverter converter;

    public GenericConverter converter() {
        if (converter == null) {
            converter = new GenericConverter(clienteFacade);
        }
        return converter;
    }
    
    public void listar() {
        clientes = clienteFacade.listar();
    }
    
    public void novo() {
        if (paramId != null) {
            cliente = clienteFacade.buscarPorId(paramId);
        } else {
            cliente = new Cliente();
            cliente.setLogradouro(new Logradouro());
            cliente.setContato(new Contato());
        }
    }
    
    public String salvar() {
        log.info("Salvando cliente '{}'.", cliente.getNome());
        cliente.setAtivo(Boolean.TRUE);
        clienteFacade.salvar(cliente);
        return "list?faces-redirect=true";
    }
    
    public String editar(Cliente u) {
        return "form?faces-redirect=true&&id=" + u.getId();
    }
    
    public void excluir(Cliente u) {
        log.info("Excluindo cliente '{}'.", u.getNome());
        clienteFacade.excluir(u);
        listar();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensagem",  "Cliente excluído com sucesso!"));
    }
    
    public List<Cliente> autoComplete(String cons) {
        return clienteFacade.autoComplete("nome", cons);
    }
}
