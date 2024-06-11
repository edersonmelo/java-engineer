/*
 * www.votacao.com.br
 */
package br.com.votacao.api.test;

import br.com.votacao.api.controladores.rest.votacaoController;
import br.com.votacao.api.dominio.votacao;
import br.com.votacao.api.dto.votacaoDTO;
import br.com.votacao.api.repository.votacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Classe de teste do endpoint de votacao
 * @author Ederson Melo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    votacaoController.class
})
public class votacaoTest {

    //URL base para acesso desse controlador
    private final String BASE_URL = "/votacao";

    //Instância do ObjectMapper para trabalhar com JSON
    private ObjectMapper objectMapper;

    //Controlador REST tratado por meio de injeção de dependências
    @Autowired
    private votacaoController restController;

    //Instância do MockMVC
    private MockMvc mockMvc;

    //Instância do mock repository
    @MockBean
    private votacaoRepository mockRepository;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    public void buscar_id_200() throws Exception {

        votacao votacao = new votacao();
        votacao.setId(1);
        votacao.setPauta("Contratar o Ederson");
        votacao.setVoto("SIM");

        when(mockRepository.findById(1)).thenReturn(Optional.of(votacao));

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pauta", is("Contratar o Ederson")))
                .andExpect(jsonPath("$.voto", is("SIM")));

        verify(mockRepository, times(1)).findById(1);
    }

    @Test
    public void buscar_id_404() throws Exception {
        mockMvc.perform(get(BASE_URL + "/2")).andExpect(status().isNotFound());
    }

    @Test
    public void criar_200() throws Exception {

        votacaoDTO dto = new votacaoDTO();
        dto.setPauta("Contratar o Ederson");
        dto.setVoto("NÃO");

        votacao votacao = new votacao();
        votacao.setId(1);
        votacao.setPauta(dto.getPauta());
        votacao.setVoto(dto.getVoto());

        when(mockRepository.save(any(votacao.class))).thenReturn(votacao);

        mockMvc.perform(post(BASE_URL)
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.pauta", is("Contratar o Ederson")))
                .andExpect(jsonPath("$.voto", is("SIM")));

        verify(mockRepository, times(1)).save(any(votacao.class));

    }

    @Test
    public void atualizar_200() throws Exception {

        votacaoDTO dto = new votacaoDTO();
        dto.setPauta("Contratar o Ederson");
        dto.setVoto("SIM");

        votacao votacao = new votacao();
        votacao.setId(1);
        votacao.setPauta(dto.getPauta());
        votacao.setVoto(dto.getVoto());

        when(mockRepository.findById(1)).thenReturn(Optional.of(votacao));
        when(mockRepository.save(any(votacao.class))).thenReturn(votacao);

        mockMvc.perform(put(BASE_URL + "/1")
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void deletar_200() throws Exception {

        votacao votacao = new votacao();
        votacao.setId(1);

        when(mockRepository.findById(1)).thenReturn(Optional.of(votacao));

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isOk());

        verify(mockRepository, times(1)).deleteById(1);
    }

}
