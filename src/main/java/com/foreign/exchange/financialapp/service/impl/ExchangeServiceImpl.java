package com.foreign.exchange.financialapp.service.impl;

import com.foreign.exchange.financialapp.dto.ExchangeRateDto;
import com.foreign.exchange.financialapp.dto.ExchangeValueDto;
import com.foreign.exchange.financialapp.entity.ExchangeValueEntity;
import com.foreign.exchange.financialapp.enums.ErrorCode;
import com.foreign.exchange.financialapp.repository.IExchangeValueRepository;
import com.foreign.exchange.financialapp.response.Result;
import com.foreign.exchange.financialapp.service.IExchangeService;
import com.foreign.exchange.financialapp.util.ExchangeApiUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toMap;

@Service("ExchangeServiceImpl")
public class ExchangeServiceImpl implements IExchangeService {

    @Value("${ratesapi.path}")
    private String ratesApiPath;

    @Autowired
    private IExchangeValueRepository iExchangeValueRepository;

    @Override
    public ExchangeRateDto getExchangeRate(String fromPair, String toPair) {
        Result result = null;
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String s = String.format("%s?base=%s&symbols=%s", ratesApiPath, fromPair, toPair);
            ResponseEntity<String> exchange = restTemplate.exchange(s, HttpMethod.GET, ExchangeApiUtility.getEntity(), String.class);
            result = new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription());
            ExchangeApiUtility.convertJsonObjectToExchangeRateDto(exchange.getBody(), exchangeRateDto);
        } catch (Exception e) {
            result = new Result(ErrorCode.EXCEPTION.getCode(), ErrorCode.EXCEPTION.getDescription());
        }
        exchangeRateDto.setResult(result);
        return exchangeRateDto;
    }

    @Override
    public ExchangeValueDto retrieveExchangeValue(BigDecimal amount, String fromPair, String toPair) {
        Result result = null;
        RestTemplate restTemplate = new RestTemplate();
        ExchangeValueDto exchangeValueDto = new ExchangeValueDto();
        try {
            String s = String.format("%s?base=%s&symbols=%s", ratesApiPath, fromPair, toPair);
            ResponseEntity<String> exchange = restTemplate.exchange(s, HttpMethod.GET, ExchangeApiUtility.getEntity(), String.class);
            BigDecimal exchangeRate = ExchangeApiUtility.getExchangeRate(exchange);
            exchangeValueDto.setTargetCurrencyAmount(Objects.requireNonNull(exchangeRate).multiply(amount));
            exchangeValueDto.setTransactionId((long) (Math.random() * 100000));
            result = new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription());
        } catch (Exception e) {
            result = new Result(ErrorCode.EXCEPTION.getCode(), ErrorCode.EXCEPTION.getDescription());
        }
        exchangeValueDto.setResult(result);
        return exchangeValueDto;
    }

    @Override
    public Map<Integer, List<ExchangeValueDto>> getListOfConversion(Long transactionId, Date transactionDate, Integer pageSize) {
        List<ExchangeValueDto> list = new ArrayList<>();
        try {
            Iterable<ExchangeValueEntity> exchangeValueEntities = iExchangeValueRepository.findAll();

            List<ExchangeValueDto> exchangeValueDtos = ExchangeApiUtility.entityToDtoObject(exchangeValueEntities);

            if (!Objects.isNull(transactionId)) {
                list = exchangeValueDtos.stream().filter(exchangeValueDto -> exchangeValueDto.getTransactionId().equals(transactionId))
                        .collect(Collectors.toList());
            }
            if (!Objects.isNull(transactionDate)) {
                list = exchangeValueDtos.stream().filter(exchangeValueDto ->
                        Objects.equals(exchangeValueDto.getTransactionDate().compareTo(transactionDate), 0))
                        .collect(Collectors.toList());
            }
            List<ExchangeValueDto> finalList = list;
            if (!Objects.equals(list.size(), 0)) {
                return IntStream.iterate(0, i -> i + pageSize)
                        .limit((finalList.size() + pageSize - 1) / pageSize)
                        .boxed()
                        .collect(toMap(i -> i / pageSize,
                                i -> finalList.subList(i, min(i + pageSize, finalList.size()))));
            }
            return new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
