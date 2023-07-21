package com.prueba.usuarios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.usuarios.controller.UsuarioController;
import com.prueba.usuarios.model.entity.Telefono;
import com.prueba.usuarios.model.entity.Usuario;
import com.prueba.usuarios.service.IUsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    IUsuarioService usuarioService;

    List<Telefono> telefonos = new ArrayList<Telefono>();
    Map<String, Object> respuesta = new HashMap<>();
    Usuario usuario1 = new Usuario(UUID.randomUUID(), "Hector Hidalgo", "Hector123", "h.hidalgo1990@gmail.com", telefonos.stream().collect(Collectors.toSet()), true, LocalDate.now(), LocalDate.now(), LocalDate.now(), "43565423423423", true, respuesta);
    Usuario usuario2 = new Usuario(UUID.randomUUID(), "Kathy Hidalgo", "Hector123", "h.hidalgo1990@gmail.com", telefonos.stream().collect(Collectors.toSet()), true, LocalDate.now(), LocalDate.now(), LocalDate.now(), "43565423423423", true, respuesta);
    Usuario usuario3 = new Usuario(UUID.randomUUID(), "Erika Espinoza", "Hector123", "h.hidalgo1990@gmail.com", telefonos.stream().collect(Collectors.toSet()), true, LocalDate.now(), LocalDate.now(), LocalDate.now(), "43565423423423", true, respuesta);

    @Test
    public void getAllRecords_success() throws Exception {
        List<Usuario> records = new ArrayList<>(Arrays.asList(usuario1, usuario2, usuario3));

        Mockito.when(usuarioService.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[2].name", is("Erika Espinoza")));
    }

    @Test
    public void createUsuarioSuccess() throws Exception {
        Usuario record = usuario2;

        Mockito.when(usuarioService.save(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()));
    }
}
