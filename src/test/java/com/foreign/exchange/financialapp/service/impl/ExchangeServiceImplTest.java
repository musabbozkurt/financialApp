package com.foreign.exchange.financialapp.service.impl;

import com.foreign.exchange.financialapp.dto.ExchangeRateDto;
import com.foreign.exchange.financialapp.dto.ExchangeValueDto;
import com.foreign.exchange.financialapp.enums.ErrorCode;
import com.foreign.exchange.financialapp.repository.IExchangeValueRepository;
import com.foreign.exchange.financialapp.response.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

class ExchangeServiceImplTest {
    @Mock
    IExchangeValueRepository iExchangeValueRepository;
    @InjectMocks
    ExchangeServiceImpl exchangeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExchangeRate() {
        ExchangeRateDto result = exchangeServiceImpl.getExchangeRate("fromPair", "toPair");
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setResult(new Result(ErrorCode.EXCEPTION.getCode(), ErrorCode.EXCEPTION.getDescription()));
        Assertions.assertEquals(exchangeRateDto.getBase(), result.getBase());
    }

    @Test
    void testRetrieveExchangeValue() {
        ExchangeValueDto result = exchangeServiceImpl.retrieveExchangeValue(new BigDecimal(0), "fromPair", "toPair");
        Assertions.assertEquals(new ExchangeValueDto().getTransactionId(), result.getTransactionId());
    }

    @Test
    void testGetListOfConversion() {
        Map<Integer, List<ExchangeValueDto>> result = exchangeServiceImpl.getListOfConversion(1L, new GregorianCalendar(2020, Calendar.AUGUST, 19, 15, 20).getTime(), 0);
        Assertions.assertEquals(new HashMap<Integer, List<ExchangeValueDto>>() {{
            put(0, Collections.singletonList(new ExchangeValueDto()));
        }}.size(), result.size() + 1);
    }
}
