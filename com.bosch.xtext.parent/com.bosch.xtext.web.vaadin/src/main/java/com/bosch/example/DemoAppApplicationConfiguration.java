package com.bosch.example;

import java.util.concurrent.ExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xtext.example.mydsl.web.MyDslWebSetup;
import org.xtext.example.mydsl.web.SuggestService;
import org.xtext.example.mydsl.web.XTextParsingService;

import com.bosch.example.rsql.SuggestOracle;
import com.google.inject.Injector;
import com.google.inject.Provider;

@Configuration
public class DemoAppApplicationConfiguration {


    @Bean(destroyMethod="tearDown")
    public SuggestOracle suggestOracle() {
        return createSuggestService();
    }
    
    private SuggestService createSuggestService(){
    	XTextParsingService parsingService = new XTextParsingService();
    	
    	SuggestService suggestService = new SuggestService(parsingService);
    	Injector injector = new MyDslWebSetup(new Provider<ExecutorService>() {
			
			@Override
			public ExecutorService get() {
				return suggestService.getExecutorService();
			}
		}).createInjectorAndDoEMFRegistration();
    	injector.injectMembers(parsingService);
    	return suggestService;
    }
    

}