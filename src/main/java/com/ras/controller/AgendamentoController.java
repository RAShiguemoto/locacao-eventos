package com.ras.controller;

import com.ras.facade.AgendamentoFacade;
import com.ras.model.Agendamento;
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
public class AgendamentoController implements Serializable {
    private Agendamento agendamento;
    private List<Agendamento> agendamentos = new ArrayList<>();
    private Long paramId;
    
    @Inject
    private AgendamentoFacade agendamentoFacade;
    
    public void listar() {
        agendamentos = agendamentoFacade.listar();
    }
    
    public void novo() {
        if (paramId != null) {
            agendamento = agendamentoFacade.buscarPorId(paramId);
        } else {
            agendamento = new Agendamento();
        }
    }
    
    public void setarValorDiaria() {
        if (agendamento.getPropriedade() != null) {
            agendamento.setValorDiaria(agendamento.getPropriedade().getValorDiaria());
        }
    }
    
    public void calculaTotal() {
        agendamento.setValorTotal(agendamento.getQuantidadeDiaria().multiply(agendamento.getValorDiaria()));
        agendamento.setValorComDesconto(agendamento.getValorTotal().subtract(agendamento.getDesconto()));
    }
    
    public String salvar() {
        log.info("Salvando usuário '{}'.", agendamento.getId());
        agendamentoFacade.salvar(agendamento);
        return "list?faces-redirect=true";
    }
    
    public String editar(Agendamento ag) {
        return "form?faces-redirect=true&&id=" + ag.getId();
    }
    
    public void excluir(Agendamento ag) {
        log.info("Excluindo usuário '{}'.", ag.getId());
        agendamentoFacade.excluir(ag);
        listar();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensagem",  "Usuário excluído com sucesso!"));
    }
}
