/*
 * www.votacao.com.br
 */
package br.com.votacao.api.repository;

import br.com.votacao.api.dominio.votacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório de votacao
 *
 * @author Ederson Melo
 */
@Repository
public interface VotacaoRepository extends PagingAndSortingRepository<votacao, Integer> {

    public Page<votacao> findAll(Pageable pageable);

    @Query("SELECT p FROM votacao p "
            + "WHERE lower(pauta) like %:busca% ")
    public Page<votacao> busca(@Param("busca") String busca, Pageable pageable);

}
