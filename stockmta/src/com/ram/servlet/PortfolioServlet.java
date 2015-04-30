/*
 * portfolio servlet - present portfolio data in web page
 */
package com.ram.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ram.model.Portfolio;
import com.ram.service.PortfolioManager;

public class PortfolioServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PortfolioManager portfolioManager=new PortfolioManager();
		Portfolio portfolio=portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio.getHtmlString());
	}
	
}
