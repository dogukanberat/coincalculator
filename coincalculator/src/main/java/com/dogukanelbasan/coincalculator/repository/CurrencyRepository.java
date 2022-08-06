package com.dogukanelbasan.coincalculator.repository;

import com.dogukanelbasan.coincalculator.entity.Currency;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Currency>{

    @Cacheable("currency")
    Currency findByCurrency(String currency);


    List<Currency> findAllByOrderByCurrencyIdAsc();

    @Override
    @CacheEvict(value = "currency", allEntries = true)
    Currency save(Currency currency);

    @Override
    @CacheEvict(value = "currency", allEntries = true)
    void delete(Currency currency);
}
