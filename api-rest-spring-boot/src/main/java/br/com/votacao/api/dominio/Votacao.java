/*
 * www.votacao.com.br
 */
package br.com.votacao.api.dominio;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidade votacao
 *
 * @author Ederson Melo
 */
@Entity
@Table(name = "votacao")
public class Votacao {

    @Id
    @SequenceGenerator(name = "votacao_seq", sequenceName = "votacao_seq", allocationSize = 1)
    @GeneratedValue(generator = "votacao_seq", strategy = GenerationType.AUTO)
    private int id;

    private String pauta;

    private String voto;

    @ApiModelProperty(notes = "Identificador do votacao")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiModelProperty(notes = "Nome da pauta")
    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    @ApiModelProperty(notes = "Valor do votacao")
    public String getVoto() {
        return voto;
    }

    public void setVoto(string voto) {
        this.voto = voto;
    }

}
