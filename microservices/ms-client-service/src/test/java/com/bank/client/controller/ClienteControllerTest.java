package com.bank.client.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void estadoDevuelveHttp200YJson() throws Exception {
        mockMvc.perform(get("/api/v1/clientes/estado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.servicio").value("client-service"))
                .andExpect(jsonPath("$.estado").value("OPERATIVO"));
    }
}
