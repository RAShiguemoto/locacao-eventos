package com.ras.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author raula
 */

@Entity
@Data
@NoArgsConstructor
public class Propriedade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @Embedded
    private Logradouro logradouro;
    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;
    
    @PrePersist
    public void prePersist() {
        nome = nome.toUpperCase();
        descricao = descricao.toUpperCase();
        logradouro.setEndereco(logradouro.getEndereco().toUpperCase());
        logradouro.setBairro(logradouro.getBairro().toUpperCase());
        logradouro.setComplemento(logradouro.getComplemento().toUpperCase());
    }
    
    @PreUpdate
    public void preUpdate() {
        nome = nome.toUpperCase();
        descricao = descricao.toUpperCase();
        logradouro.setEndereco(logradouro.getEndereco().toUpperCase());
        logradouro.setBairro(logradouro.getBairro().toUpperCase());
        logradouro.setComplemento(logradouro.getComplemento().toUpperCase());
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
