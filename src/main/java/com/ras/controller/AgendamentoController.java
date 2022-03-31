package com.ras.controller;

import com.ras.facade.AgendamentoFacade;
import com.ras.model.Agendamento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;

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
            calculaTotal();
        }
    }
    
    public void atualizarDiasETotais() {
        atualizarQtdDias();
        calculaTotal();
    }
    
    private Long getTimeMillisDate(Date data) {
        Calendar calendar = Calendar.getInstance(); // locale-specific
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    
    private void atualizarQtdDias() {
        long time1 = getTimeMillisDate(agendamento.getDataInicialDiaria());
        long time2 = getTimeMillisDate(agendamento.getDataFinalDiaria());
        long finalTime = time2 - time1;
        long dias = (((finalTime / 1000) / 60) / 60) / 24;
        agendamento.setQuantidadeDiaria(new BigDecimal(dias));
        agendamento.setQuantidadeDiaria(agendamento.getQuantidadeDiaria().add(BigDecimal.ONE));
    }
    
    public void atualizarDatasETotais() {
        atualizarDatas();
        calculaTotal();
    }
    
    private void atualizarDatas() {
        DateTime dateTime = new DateTime(agendamento.getDataInicialDiaria());
        dateTime = dateTime.plusDays(agendamento.getQuantidadeDiaria().intValue() - 1);
        agendamento.setDataFinalDiaria(dateTime.toDate());
    }
    
    public void calculaTotal() {
        agendamento.setValorTotal(agendamento.getQuantidadeDiaria().multiply(agendamento.getValorDiaria()));
        agendamento.setValorTotalLiquido(agendamento.getValorTotal().subtract(agendamento.getDesconto()));
    }
    
    public String salvar() {
        log.info("Salvando agendamento '{}'.", agendamento.getCliente().getNome());
        agendamentoFacade.salvar(agendamento);
        return "list?faces-redirect=true";
    }
    
    public String editar(Agendamento ag) {
        return "form?faces-redirect=true&&id=" + ag.getId();
    }
    
    public void excluir(Agendamento ag) {
        log.info("Excluindo agendamento '{}'.", ag.getId());
        agendamentoFacade.excluir(ag);
        listar();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Mensagem",  "Agendamento excluído com sucesso!"));
    }
}
