package com.foreign.exchange.financialapp.service;

import com.foreign.exchange.financialapp.dto.ExchangeRateDto;
import com.foreign.exchange.financialapp.dto.ExchangeValueDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public interface IExchangeService {
    ExchangeRateDto getExchangeRate(String fromPair, String toPair);

    ExchangeValueDto retrieveExchangeValue(BigDecimal amount, String fromPair, String toPair);

    Map<Integer, List<ExchangeValueDto>> getListOfConversion(Long transactionId, Date transactionDate, Integer pageSize);
}
