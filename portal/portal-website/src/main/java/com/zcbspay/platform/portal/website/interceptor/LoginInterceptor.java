package com.zcbspay.platform.portal.website.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.portal.website.util.UserHelper;


public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (UserHelper.getCurrentUser(request)==null) {
			PrintWriter out = response.getWriter();  
	        out.println("<html>");      
	        out.println("<script>");      
	        out.println("window.open ('"+request.getContextPath()+"'+'login.jsp','_top')");      
	        out.println("</script>");      
	        out.println("</html>");   
	        return false;
		}else{
			return true;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModelAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后。
		//响应用户之后。

	}

}
