package com.bank.account.controller;

import com.bank.account.dto.CrearCuentaRequest;
import com.bank.account.dto.CuentaResponse;
import com.bank.account.service.CuentaService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {
    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crear(@Valid @RequestBody CrearCuentaRequest request) {
        CuentaResponse cuenta = service.crear(request);
        return ResponseEntity.created(URI.create("/api/v1/cuentas/" + cuenta.id())).body(cuenta);
    }

    @GetMapping("/{id}")
    public CuentaResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }
}
