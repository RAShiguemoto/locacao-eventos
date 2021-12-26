package com.ras.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author RAS - DESKTOP
 */

@Embeddable
@Data
@NoArgsConstructor
public class Contato implements Serializable {
    @Column(name = "telefone_fixo")
    private String telefoneFixo;
    private String celular;
    private String email;
}
