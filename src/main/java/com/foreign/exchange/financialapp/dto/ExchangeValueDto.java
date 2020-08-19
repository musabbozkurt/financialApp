package com.foreign.exchange.financialapp.dto;

import com.foreign.exchange.financialapp.response.Result;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeValueDto {
    private Long transactionId;
    private BigDecimal targetCurrencyAmount;
    private Date transactionDate;
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
