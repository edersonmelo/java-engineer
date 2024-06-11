/*
 * www.votacao.com.br
 */
package br.com.votacao.api.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * DTO de votacao
 *
 * @author Ederson Melo
 */
public class VotacaoDTO {

    private String pauta;
    private String voto;

    @ApiModelProperty(notes = "Pauta do votacao")
    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    public String getVoto() {
        return voto;
    }

    @ApiModelProperty(notes = "Valor do votacao")
    public void setVoto(String voto) {
        this.voto = voto;
    }

}
