package com.bank.account.repository;

import com.bank.account.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);
}
