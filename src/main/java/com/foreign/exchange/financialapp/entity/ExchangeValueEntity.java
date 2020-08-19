package com.foreign.exchange.financialapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "EXCHANGE_VALUE")
public class ExchangeValueEntity {
    @Id
    private Integer id;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "target_currency_amount")
    private BigDecimal targetCurrencyAmount;

    @Column(name = "transaction_date")
    private Date transactionDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getTargetCurrencyAmount() {
        return targetCurrencyAmount;
    }

    public void setTargetCurrencyAmount(BigDecimal targetCurrencyAmount) {
        this.targetCurrencyAmount = targetCurrencyAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}