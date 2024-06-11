package com.example.tit.controller;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//import com.example.tit.controller.PacijentController;
import com.example.tit.dao.PacijentRepository;
import com.example.tit.model.Pacijent_Nada;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PacijentController.class)
public class PacijentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacijentRepository pacijentRepository;

    @Test
    public void testGetAllPacijenti() throws Exception {
        Pacijent_Nada pacijentNada1 = new Pacijent_Nada("Ime1", "Prezime1");
        Pacijent_Nada pacijentNada2 = new Pacijent_Nada("Ime2", "Prezime2");
        when(pacijentRepository.findAll()).thenReturn(Stream.of(pacijentNada1, pacijentNada2).collect(Collectors.toList()));

        mockMvc.perform(MockMvcRequestBuilders.get("/pacijenti")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].ime").value("Ime1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].prezime").value("Prezime2"));
    }

    @Test
    public void testGetPacijentById() throws Exception {
        Pacijent_Nada pacijentNada = new Pacijent_Nada("Ime", "Prezime");
        when(pacijentRepository.findById(1)).thenReturn(Optional.of(pacijentNada));

        mockMvc.perform(MockMvcRequestBuilders.get("/pacijenti/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ime").value("Ime"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.prezime").value("Prezime"));
    }

    @Test
    public void testDeletePacijent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/pacijenti/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
