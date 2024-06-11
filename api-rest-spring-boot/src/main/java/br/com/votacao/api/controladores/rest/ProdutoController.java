/*
 * www.votacao.com.br
 */
package br.com.votacao.api.controladores.rest;

import br.com.votacao.api.ApiError;
import br.com.votacao.api.PageableFactory;
import br.com.votacao.api.dominio.votacao;
import br.com.votacao.api.dto.votacaoDTO;
import br.com.votacao.api.repository.votacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controlador de votacao
 *
 * @author Ederson Melo
 */
@RestController
@CrossOrigin
@RequestMapping("/votacao")
@Api(tags = "votacao", description = "API de votacao")
public class votacaoController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    votacaoRepository votacaoRepository;

    @ApiOperation(value = "Lista os votos")
    @GetMapping()
    public Page<votacao> listar(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size,
            @RequestParam(
                    value = "sort",
                    required = false) String sort,
            @RequestParam(
                    value = "q",
                    required = false) String q
    ) {
        Pageable pageable = new PageableFactory(page, size, sort).getPageable();

        Page<votacao> resultPage;

        if (q == null) {
            resultPage = votacaoRepository.findAll(pageable);
        } else {
            resultPage = votacaoRepository.busca(q.toLowerCase(), pageable);
        }

        return resultPage;
    }

    @ApiOperation(value = "Busca um votacao pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<votacao> listar(@PathVariable Integer id) {
        Optional<votacao> rastreador = votacaoRepository.findById(id);

        if (!rastreador.isPresent()) {
            return ApiError.notFound("votacao não encontrado");
        }

        return new ResponseEntity<>(rastreador.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Cria um novo votacao")
    @PostMapping()
    public ResponseEntity<votacao> criar(@RequestBody votacaoDTO dto, UriComponentsBuilder ucBuilder) {
        try {
            //Crio um objeto da entidade preenchendo com os valores do DTO e validando
            votacao votacao = new votacao();

            if (dto.getPauta() == null || dto.getPauta().length() < 2) {
                return ApiError.badRequest("Informe o nome da pauta");
            }
            votacao.setPauta(dto.getPauta());

            if (dto.getVoto() == null || dto.getVoto() <= 0) {
                return ApiError.badRequest("Valor do voto é inválido");
            }
            votacao.setVoto(dto.getVoto());

            votacao novo = votacaoRepository.save(votacao);

            //Se ocorreu algum erro, retorno esse erro para a API
            //Avisando que a votação não foi efetivada
            if (novo == null) {
                return ApiError.badRequest("Ocorreu algum erro com seu voto, a votação foi anulada.");
            }

            //Se foi criado com sucesso, retorno o objeto criado
            //Avisando que a votação não foi efetivada
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Erro ao criar um voto", e);
            return ApiError.internalServerError("Ocorreu algum erro com seu voto, a votação foi anulada.");
        }
    }

    //Cuidado: O uso devido a possibilidade de atualizar o voto pode causar fraude
    //Cuidado: O uso devido a possibilidade de atualizar a pauta pode causar fraude
    //Cuidado: deve ser implementado ou removido
    
    @ApiOperation(value = "Atualiza uma pauta")
    @PutMapping(value = "/{id}")
    public ResponseEntity<votacao> atualizar(@PathVariable("id") int id, @RequestBody votacaoDTO dto) {
        try {
            Optional<votacao> votacaoAtual = votacaoRepository.findById(id);

            if (!votacaoAtual.isPresent()) {
                return ApiError.notFound("Pauta não encontrado");
            }

            if (dto.getPauta() != null) {
                if (dto.getPauta().length() < 2) {
                    return ApiError.badRequest("Nome da Pauta inválido");
                }
                votacaoAtual.get().setPauta(dto.getPauta());
            }

            //Cuidado: Removi a atualizadção do voto para evitar fraudes

            //Atualizo o objeto utilizando o repositório
            votacao atualizado = votacaoRepository.save(votacaoAtual.get());

            //Se ocorreu algum erro, retorno esse erro para a API
            if (atualizado == null) {
                return ApiError.internalServerError("Erro na atualização da pauta");
            }

            //Se foi criado com sucesso, retorno o objeto atualizado
            return new ResponseEntity<>(atualizado, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar uma pauta", e);
            return ApiError.internalServerError("Erro na atualização da pauta");
        }
    }

    //Cuidado: Pode exisitir mas pode gerar fraude
    
    @ApiOperation(value = "Remove um votacao")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<votacao> deletar(@PathVariable Integer id) {
        Optional<votacao> votacao = votacaoRepository.findById(id);

        if (!votacao.isPresent()) {
            return ApiError.notFound("votacao não encontrado");
        } else {
            votacaoRepository.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
