package com.micro.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridofilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridofilter.class);

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx= RequestContext.getCurrentContext();
		
		HttpServletRequest request = ctx.getRequest();
		log.info("Entrando a post");
		long tiempoInicio=(Long) request.getAttribute("tiempoInicio");
		long tiempoFinal = System.currentTimeMillis();
		Long  tiempoTranscurrido=tiempoFinal-tiempoInicio;
		log.info(String.format("Tiempo Transcurrido en segundos:  %s", tiempoTranscurrido.doubleValue()/1000d));
		
		request.setAttribute("tiempoInicio",tiempoInicio);
		
		return null;
	}

	@Override
	public String filterType() {
		
		return "post";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}

}
