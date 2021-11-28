package com.ras.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author raula
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Boolean ativo;
    private String login;
    private String senha;
    private String permissao;
}
