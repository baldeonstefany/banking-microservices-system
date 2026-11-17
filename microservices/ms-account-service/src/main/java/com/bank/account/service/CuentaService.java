package com.bank.account.service;

import com.bank.account.dto.CrearCuentaRequest;
import com.bank.account.dto.CuentaResponse;
import com.bank.account.entity.CuentaEntity;
import com.bank.account.repository.CuentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CuentaService {
    private final CuentaRepository repository;

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CuentaResponse crear(CrearCuentaRequest request) {
        // Primera iteración: clienteId es una referencia externa aún NO verificada.
        // La validación HTTP contra ms-client-service se implementará después.
        if (repository.existsByNumeroCuenta(request.numeroCuenta())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El número de cuenta ya existe");
        }

        CuentaEntity cuenta = new CuentaEntity(
                request.clienteId(), request.numeroCuenta(), request.saldoInicial(), request.moneda());
        return aResponse(repository.save(cuenta));
    }

    @Transactional(readOnly = true)
    public CuentaResponse buscar(Long id) {
        CuentaEntity cuenta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
        return aResponse(cuenta);
    }

    private CuentaResponse aResponse(CuentaEntity cuenta) {
        return new CuentaResponse(cuenta.getId(), cuenta.getClienteId(),
                cuenta.getNumeroCuenta(), cuenta.getSaldo(), cuenta.getMoneda());
    }
}
