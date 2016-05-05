package com.truemega.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.truemega.logger.LoggerService;

public class AuthFilter implements Filter {
	private LoggerService loggerService = new LoggerService();

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		loggerService.logPortalInfo(" start doFilter method of AuthFilter ");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(true);
		String requestURL = httpServletRequest.getRequestURI();

		try {
			if (session.getAttribute("employee") != null
					&& requestURL.indexOf("/login.xhtml") >= 0)
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/faces/index.xhtml");

			if (session.getAttribute("employee") != null
					|| requestURL.indexOf("/login.xhtml") >= 0
					|| requestURL.contains("css"))
				chain.doFilter(httpServletRequest, httpServletResponse);
			else {
				loggerService.logPortalInfo("else of chain doFilter");
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/faces/login.xhtml");

			}
		} catch (Exception e) {
			loggerService.logPortalError(" filter error  ", null);
		}
		loggerService.logPortalInfo(" end doFilter method of AuthFilter ");
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
