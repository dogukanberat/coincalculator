package com.dogukanelbasan.coincalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY")
public class Currency {

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


}
