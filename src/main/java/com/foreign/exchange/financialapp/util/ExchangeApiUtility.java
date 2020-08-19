package com.foreign.exchange.financialapp.util;

import com.foreign.exchange.financialapp.dto.ExchangeRateDto;
import com.foreign.exchange.financialapp.dto.ExchangeValueDto;
import com.foreign.exchange.financialapp.entity.ExchangeValueEntity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class ExchangeApiUtility {
    public static HttpEntity<String> getEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }

    public static BigDecimal getExchangeRate(ResponseEntity<String> exchange) {
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(Objects.requireNonNull(exchange.getBody()));
        Optional<Map.Entry<String, JsonElement>> rates = object.get("rates").getAsJsonObject().entrySet().stream().findFirst();
        return rates.map(stringJsonElementEntry -> stringJsonElementEntry.getValue().getAsBigDecimal()).orElse(null);
    }

    public static void convertJsonObjectToExchangeRateDto(String body, ExchangeRateDto exchangeRateDto) throws ParseException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(body).getAsJsonObject();
        exchangeRateDto.setBase(jsonObject.get("base").getAsString());
        Optional<Map.Entry<String, JsonElement>> rates = jsonObject.get("rates").getAsJsonObject().entrySet().stream().findFirst();
        exchangeRateDto.setRates(rates.map(Object::toString).orElse(null));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        exchangeRateDto.setDate(formatter.parse(jsonObject.get("date").getAsString()));
    }

    public static List<ExchangeValueDto> entityToDtoObject(Iterable<ExchangeValueEntity> exchangeValueEntities) {
        List<ExchangeValueDto> exchangeValueDtos = new ArrayList<>();
        exchangeValueEntities.iterator().forEachRemaining(exchangeValueEntity -> {
            ExchangeValueDto exchangeValueDto = new ExchangeValueDto();
            exchangeValueDto.setTransactionId(exchangeValueEntity.getTransactionId());
            exchangeValueDto.setTargetCurrencyAmount(exchangeValueEntity.getTargetCurrencyAmount());
            exchangeValueDto.setTransactionDate(exchangeValueEntity.getTransactionDate());
            exchangeValueDtos.add(exchangeValueDto);
        });
        return exchangeValueDtos;
    }

}
