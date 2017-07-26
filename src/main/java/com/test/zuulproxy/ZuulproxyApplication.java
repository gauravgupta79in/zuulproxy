package com.test.zuulproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.spring.security.aad.auth.filter.AdalFilter;

@SpringBootApplication
@EnableAutoConfiguration 
@EnableEurekaClient
@EnableZuulProxy
public class ZuulproxyApplication {

	private @Autowired AutowireCapableBeanFactory beanFactory;

	private String authenticatedpaths = "/secure/*";

	@Bean
	public FilterRegistrationBean adalFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		AdalFilter adalFilter = new AdalFilter();
		beanFactory.autowireBean(adalFilter);
		registration.setFilter(adalFilter);
		registration.addUrlPatterns(authenticatedpaths);
		registration.setOrder(1);
		return registration;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulproxyApplication.class, args);
	}
}
