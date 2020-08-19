package com.foreign.exchange.financialapp.controller;

import com.foreign.exchange.financialapp.dto.ExchangeRateDto;
import com.foreign.exchange.financialapp.dto.ExchangeValueDto;
import com.foreign.exchange.financialapp.service.IExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

class ExchangeControllerTest {
    @Mock
    IExchangeService iExchangeService;
    @InjectMocks
    ExchangeController exchangeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetExchangeRate() {
        when(iExchangeService.getExchangeRate(anyString(), anyString())).thenReturn(new ExchangeRateDto());

        ExchangeRateDto result = exchangeController.getExchangeRate("fromPair", "toPair");
        Assertions.assertEquals(new ExchangeRateDto().getBase(), result.getBase());
    }

    @Test
    void testRetrieveExchangeValue() {
        when(iExchangeService.retrieveExchangeValue(any(), anyString(), anyString())).thenReturn(new ExchangeValueDto());

        ExchangeValueDto result = exchangeController.retrieveExchangeValue(new BigDecimal(0), "fromPair", "toPair");
        Assertions.assertEquals(new ExchangeValueDto().getTransactionId(), result.getTransactionId());
    }

    @Test
    void testGetListOfConversion() {
        when(iExchangeService.getListOfConversion(anyLong(), any(), anyInt())).thenReturn(new HashMap<Integer, List<ExchangeValueDto>>() {{
            put(0, Collections.singletonList(new ExchangeValueDto()));
        }});

        Map<Integer, List<ExchangeValueDto>> result = exchangeController.getListOfConversion(1L, null, 0);
        Assertions.assertEquals(new HashMap<Integer, List<ExchangeValueDto>>() {{
            put(0, Collections.singletonList(new ExchangeValueDto()));
        }}.size(), result.size());
    }

}
