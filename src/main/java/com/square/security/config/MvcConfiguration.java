package com.square.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{
	 	@Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/view/");
	        resolver.setSuffix(".jsp");
	        resolver.setViewClass(JstlView.class);
	        registry.viewResolver(resolver);
	    }
}