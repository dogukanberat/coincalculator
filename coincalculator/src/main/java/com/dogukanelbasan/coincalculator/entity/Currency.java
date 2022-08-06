package com.dogukanelbasan.coincalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    @Id
    @SequenceGenerator(name = "CURRENCY_Id_GENERATOR", sequenceName = "CURRENCY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_Id_GENERATOR")
    @Column(name = "id")
    private Long currencyId;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "SPENDABLE")
    private Boolean spendable;

    @Column(name = "RECEIVABLE")
    private Boolean receivable;

    @Column(name = "MAX_SPEND_AMOUNT")
    private Integer maxSpendAmount;

    @Column(name = "MIN_SPEND_AMOUNT")
    private Integer minSpendAmount;

    @Column(name = "IS_FIAT_CURRENCY")
    private Boolean isFiatCurrency;



}
