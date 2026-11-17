package com.bank.account.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CrearCuentaRequest(
        @NotNull @Positive Long clienteId,
        @NotBlank @Size(max = 30) String numeroCuenta,
        @NotNull @DecimalMin("0.00") @Digits(integer = 17, fraction = 2) BigDecimal saldoInicial,
        @NotBlank @Pattern(regexp = "PEN|USD") String moneda
) { }
