package com.foreign.exchange.financialapp.controller;

import com.foreign.exchange.financialapp.dto.ExchangeRateDto;
import com.foreign.exchange.financialapp.dto.ExchangeValueDto;
import com.foreign.exchange.financialapp.enums.ErrorCode;
import com.foreign.exchange.financialapp.service.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class ExchangeController implements ErrorController {

    @Autowired
    @Qualifier(value = "ExchangeServiceImpl")
    private IExchangeService iExchangeService;

    //Exchange Rate API
    @GetMapping("/getExchangeRate")
    public ExchangeRateDto getExchangeRate(@RequestParam(name = "fromPair", defaultValue = "EUR") String fromPair,
                                            @RequestParam(name = "toPair", defaultValue = "USD") String toPair) {
        return iExchangeService.getExchangeRate(fromPair, toPair);
    }

    //Conversion API
    @GetMapping("/retrieveExchangeValue")
    public ExchangeValueDto retrieveExchangeValue(@RequestParam(name = "amount") BigDecimal amount,
                                                  @RequestParam(name = "fromPair", defaultValue = "USD") String fromPair,
                                                  @RequestParam(name = "toPair", defaultValue = "EUR") String toPair) {
        return iExchangeService.retrieveExchangeValue(amount, fromPair, toPair);
    }

    //Conversion List API
    @GetMapping("/getListOfConversion")
    public Map<Integer, List<ExchangeValueDto>> getListOfConversion(@RequestParam(name = "transactionId", defaultValue = "") Long transactionId,
                                                                    @RequestParam(name = "transactionDate", defaultValue = "") Date transactionDate,
                                                                    @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize) {
        return iExchangeService.getListOfConversion(transactionId, transactionDate, pageSize);
    }

    //check url path is valid or not
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return ErrorCode.CHECK_PARAMATER.getDescription();
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ErrorCode.INTERNAL_ERROR.getDescription() + ErrorCode.CHECK_PARAMATER.getDescription();
            }
        }
        return ErrorCode.CHECK_PARAMATER.getDescription();
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
