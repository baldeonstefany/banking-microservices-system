package com.bank.client.controller;

import com.bank.client.dto.EstadoServicioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @GetMapping("/estado")
    public ResponseEntity<EstadoServicioResponse> consultarEstado() {
        EstadoServicioResponse respuesta =
                new EstadoServicioResponse("client-service", "OPERATIVO");
        return ResponseEntity.ok(respuesta);
    }
}
