package com.bank.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador externo: no hay dependencia JPA hacia ClienteEntity.
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 30)
    private String numeroCuenta;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Column(nullable = false, length = 3)
    private String moneda;

    protected CuentaEntity() { }

    public CuentaEntity(Long clienteId, String numeroCuenta, BigDecimal saldo, String moneda) {
        this.clienteId = clienteId;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.moneda = moneda;
    }

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public BigDecimal getSaldo() { return saldo; }
    public String getMoneda() { return moneda; }
}
