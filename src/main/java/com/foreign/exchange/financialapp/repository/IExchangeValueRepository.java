package com.foreign.exchange.financialapp.repository;

import com.foreign.exchange.financialapp.entity.ExchangeValueEntity;
import org.springframework.data.repository.CrudRepository;

public interface IExchangeValueRepository extends CrudRepository<ExchangeValueEntity, Integer> {

}
