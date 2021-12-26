package com.ras.controller;

import com.ras.facade.ClienteFacade;
import com.ras.model.Cliente;
import com.ras.model.Contato;
import com.ras.model.Logradouro;
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
public class ClienteController implements Serializable {
    private Cliente cliente;
    private List<Cliente> clientes = new ArrayList<>();
    private Long paramId;
    
    @Inject
    private ClienteFacade clienteFacade;
    
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
        cliente.setAtivo(Boolean.TRUE);
        clienteFacade.salvar(cliente);
        return "list?faces-redirect=true";
    }
    
    public String editar(Cliente u) {
        return "form?faces-redirect=true&&id=" + u.getId();
    }
    
    public void excluir(Cliente u) {
        clienteFacade.excluir(u);
        listar();
    }
}
