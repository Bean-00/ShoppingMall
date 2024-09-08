package com.model2.mvc.framework;

import com.model2.mvc.service.user.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Action {
	
	private ServletContext servletContext;
	
	public Action(){
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public <T> T getBean(String beanName, Class<T> clazz) {
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		return context.getBean(beanName, clazz);
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}