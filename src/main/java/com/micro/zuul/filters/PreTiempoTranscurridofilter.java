package com.micro.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridofilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridofilter.class);

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx= RequestContext.getCurrentContext();
		
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request en rutado %s", request.getMethod(),request.getRequestURL().toString()));
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio",tiempoInicio);
		
		return null;
	}

	@Override
	public String filterType() {
		
		return "pre";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}

}
