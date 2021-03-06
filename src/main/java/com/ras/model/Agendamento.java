package com.ras.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RAS - DESKTOP
 */

@Entity
@Getter
@Setter
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLancamento;
    
    @Column(name = "data_inicial_diaria")
    @Temporal(TemporalType.DATE)
    private Date dataInicialDiaria;
    
    @Column(name = "data_final_diaria")
    @Temporal(TemporalType.DATE)
    private Date dataFinalDiaria;
    
    @Column(name = "quantidade_diaria")
    private BigDecimal quantidadeDiaria;
    
    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;
    
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    
    @Column(name = "desconto")
    private BigDecimal desconto;
    
    @Column(name = "valor_total_loquido")
    private BigDecimal valorTotalLiquido;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Propriedade propriedade;

    public Agendamento() {
        this.valorDiaria = BigDecimal.ZERO;
        this.quantidadeDiaria = BigDecimal.ONE;
        this.valorTotal = BigDecimal.ZERO;
        this.desconto = BigDecimal.ZERO;
        this.valorTotalLiquido = BigDecimal.ZERO;
        this.dataInicialDiaria = new Date();
        this.dataFinalDiaria = new Date();
    }
    
    @PrePersist
    public void PrePersist() {
        this.dataLancamento = new Date();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agendamento other = (Agendamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
