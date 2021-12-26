package com.ras.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author RAS - DESKTOP
 */

@Embeddable
@Data
@NoArgsConstructor
public class Logradouro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String endereco;
    private String numero;
    private String bairro;
    private String complemento;
    private String cep;
    
    @ManyToOne
    private Cidade cidade;
}
