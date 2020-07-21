package com.lly.util;

import com.lly.pojo.Users;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author 86177
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor());
		//同一异常和无界面
		System.out.println("...............");

		interceptorRegistration.addPathPatterns("/*")
		.excludePathPatterns("/error.html","/login.html","/regist.html","/login","/regist");
		
		
		//interceptorRegistration.addPathPatterns("/**");
		
		
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	private class MyInterceptor implements HandlerInterceptor{

		
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			System.out.println("在控制器之前调用");
			// TODO Auto-generated method stub
			//Users us = (Users)request.getSession().getAttribute("user");
			//String name = us.getUname();
			Users users = (Users) request.getSession().getAttribute("users");

			if(users == null) {
				System.out.println("当前用户未登录！");
				response.sendRedirect("/login.html");
				return false;
			}
			
			return true;
		}
		
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
				//controller方法处理完毕后调用此方法
				System.out.println("在后端控制器执行后调用");
		}



		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				Exception ex) throws Exception {
			//页面渲染完毕后调用此方法
			System.out.println("整个请求执行完毕后调用");
		}
		
	}

}
