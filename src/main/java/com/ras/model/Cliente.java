package com.ras.model;

import java.io.Serializable;
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
 * @author RAS - DESKTOP
 */

@Entity
@Data
@NoArgsConstructor
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    @Column(name = "cpf_ou_cnpj")
    private String cpfOuCnpj;
    @Embedded
    private Logradouro logradouro;
    @Embedded
    private Contato contato;
    
    @PrePersist
    public void prePersist() {
        nome = nome.toUpperCase();
        logradouro.setEndereco(logradouro.getEndereco().toUpperCase());
        logradouro.setBairro(logradouro.getBairro().toUpperCase());
        logradouro.setComplemento(logradouro.getComplemento().toUpperCase());
        contato.setEmail(contato.getEmail().toLowerCase());
    }
    
    @PreUpdate
    public void preUpdate() {
        nome = nome.toUpperCase();
        logradouro.setEndereco(logradouro.getEndereco().toUpperCase());
        logradouro.setBairro(logradouro.getBairro().toUpperCase());
        logradouro.setComplemento(logradouro.getComplemento().toUpperCase());
        contato.setEmail(contato.getEmail().toLowerCase());
    }
}
