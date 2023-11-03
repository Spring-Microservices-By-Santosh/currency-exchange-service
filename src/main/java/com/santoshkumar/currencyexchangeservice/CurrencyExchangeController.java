package com.santoshkumar.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repo;
	
    @GetMapping("/currency-exchange/from/{codeFrom}/to/{codeTo}")
    public Object retrieveExchange(@PathVariable String codeFrom, @PathVariable String codeTo) {
//      return new CurrencyExchange(100L, "USD", "INR", new BigDecimal(83), environment.getProperty("spring.application.name"));
    	CurrencyExchange currencyExchange = repo.findByCodeFromAndCodeTo(codeFrom, codeTo);
    	String service_name = environment.getProperty("spring.application.name");
    	if (currencyExchange != null) {
    		currencyExchange.setServiceName(service_name);
    		return currencyExchange;
    	}
    	else {
    		return "Unable to get a Currency Conversion Rates";
    	}
    }
}
