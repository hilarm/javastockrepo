/*
 * The class represent a portfolio servlet where portfolios are printed.
 * @author: Hila Ram
 * Last change date May, 7. 
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
		Portfolio portfolio2=portfolioManager.getPortfolio();
		portfolio2.setTitle("Portfolio#2");
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio.removeStock(0);
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.changeBid(55.5f, 2);
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	}
	
}
