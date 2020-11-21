package br.com.original.desafio.pix.controller;

import br.com.original.desafio.pix.dto.CreatePixKeyRequest;
import br.com.original.desafio.pix.dto.UpdatePixKeyRequest;
import br.com.original.desafio.pix.dto.mapper.PixKeyMapper;
import br.com.original.desafio.pix.model.KeyType;
import br.com.original.desafio.pix.model.PixKey;
import br.com.original.desafio.pix.service.PixKeyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PixKeyController.class)
public class PixKeyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PixKeyService pixKeyService;

    @MockBean
    private PixKeyMapper pixKeyMapper;

    private PixKey pixKey;

    private CreatePixKeyRequest pixKeyRequest;

    private ObjectMapper mapper = new ObjectMapper();

    private Iterable<PixKey> pixKeyIterable;

    private UpdatePixKeyRequest updatePixKeyRequest;

    @Before
    public void init(){
        pixKey = new PixKey();
        pixKey.setId(Long.valueOf(1));
        pixKey.setCustomerId(Long.valueOf(2));
        pixKey.setType(KeyType.PHONE);
        pixKey.setKey("11999999999");
        pixKey.setCreationDate(LocalDate.now());

        pixKeyRequest = new CreatePixKeyRequest();
        pixKeyRequest.setCustomerId(Long.valueOf(2));
        pixKeyRequest.setKey("11999999999");
        pixKeyRequest.setKeyType(KeyType.PHONE);

        updatePixKeyRequest = new UpdatePixKeyRequest();

        updatePixKeyRequest.setKey("11999999999");
        updatePixKeyRequest.setKeyType(KeyType.PHONE);

        List<PixKey> listPixKey = new ArrayList<>();

        listPixKey.add(pixKey);

        pixKeyIterable = listPixKey;
    }

    @Test
    public void getByCustomerIdTest() throws Exception {
        when(pixKeyService.getByCustomerId(Mockito.anyLong())).thenReturn(pixKeyIterable);
        mockMvc.perform(get("/chave/"+2))
                .andExpect(status().isOk());
    }


    @Test
    public void createTest() throws Exception {
        String pixKeyJson = mapper.writeValueAsString(pixKeyRequest);

        when(pixKeyService.create(Mockito.any(PixKey.class))).thenReturn(pixKey);
        when(pixKeyMapper.toPixKey(Mockito.any(CreatePixKeyRequest.class))).thenReturn(pixKey);

        mockMvc.perform(post("/chave")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pixKeyJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/chave/"+1+"/"+2))
                .andExpect(status().isNoContent());

    }

    @Test
    public void updateTest() throws Exception {
        String pixKeyJson = mapper.writeValueAsString(updatePixKeyRequest);

        when(pixKeyService.create(Mockito.any(PixKey.class))).thenReturn(pixKey);
        when(pixKeyMapper.toPixKey(Mockito.any(CreatePixKeyRequest.class))).thenReturn(pixKey);

        mockMvc.perform(post("/chave")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pixKeyJson))
                .andExpect(status().isOk());

    }


}
