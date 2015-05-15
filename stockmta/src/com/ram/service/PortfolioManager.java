/*
 * The class represent a portfolio manager where all portfolios are managed.
 * @author: Hila Ram 
 */
package com.ram.service;

import java.util.Calendar;
import java.util.Date;

import com.ram.model.Portfolio;
import com.ram.model.Stock;

public class PortfolioManager {
	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		//date for stock
		Calendar cal=Calendar.getInstance();
		cal.set(2014,11,15);
		Date date1=cal.getTime();
		cal.set(2014,11,15);
		Date date2=cal.getTime();
		cal.set(2014, 11,15);
		Date date3=cal.getTime();
		
		Stock stock1=new Stock("PIH" , 10f, 8.5f,date1,0);
		Stock stock2=new Stock("AAL", 30f, 25.5f ,date2,0);
		Stock stock3=new Stock("CAAS", 20f, 15.5f,date3,0);
		
		myPortfolio.buyStock(stock1, 20);
		myPortfolio.buyStock(stock2, 30);
		myPortfolio.buyStock(stock3, 40);
		
		myPortfolio.sellStock("AAL", 30);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}

}
