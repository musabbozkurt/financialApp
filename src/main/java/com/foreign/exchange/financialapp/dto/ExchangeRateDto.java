package com.foreign.exchange.financialapp.dto;

import com.foreign.exchange.financialapp.response.Result;

import java.util.Date;

public class ExchangeRateDto {

    private String base;
    private String rates;
    private Date date;
    private Result result;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
