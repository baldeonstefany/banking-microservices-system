package com.bank.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CuentaApiIntegrationTest {
    @Autowired MockMvc mvc;

    @Test
    void creaYConsultaCuenta() throws Exception {
        mvc.perform(post("/api/v1/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"clienteId":1,"numeroCuenta":"DEMO-CUENTA-001","saldoInicial":125.50,"moneda":"PEN"}
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.saldo").value(125.5));

        mvc.perform(get("/api/v1/cuentas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(1));
    }

    @Test
    void rechazaSaldoNegativo() throws Exception {
        mvc.perform(post("/api/v1/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"clienteId":1,"numeroCuenta":"DEMO-INVALIDA","saldoInicial":-1,"moneda":"PEN"}
                    """))
                .andExpect(status().isBadRequest());
    }
}
