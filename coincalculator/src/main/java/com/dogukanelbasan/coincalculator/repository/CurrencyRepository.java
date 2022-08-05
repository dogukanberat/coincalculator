package com.dogukanelbasan.coincalculator.repository;

import com.dogukanelbasan.coincalculator.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Currency>{

    Currency findByCurrency(String currency);

    List<Currency> findAllByOrderByCurrencyIdAsc();
}
