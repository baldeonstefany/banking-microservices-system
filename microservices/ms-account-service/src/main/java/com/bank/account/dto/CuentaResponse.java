package com.bank.account.dto;

import java.math.BigDecimal;

public record CuentaResponse(
        Long id, Long clienteId, String numeroCuenta, BigDecimal saldo, String moneda
) { }
