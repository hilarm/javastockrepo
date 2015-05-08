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
		Portfolio portfolio=new Portfolio();
		//from servlet4
		Calendar cal=Calendar.getInstance();
		cal.set(2014,10,15);
		Date date1=cal.getTime();
		cal.set(2014,10,15);
		Date date2=cal.getTime();
		cal.set(2014, 10,15);
		Date date3=cal.getTime();
		Stock stock1=new Stock("PIH" , 13.1f, 12.4f,date1);
		Stock stock2=new Stock("AAL", 5.78f, 5.5f ,date2);
		Stock stock3=new Stock("CAAS", 32.2f, 31.5f,date3 );
		
		//insert stock
		portfolio.addStock(stock1);
		portfolio.addStock(stock2);
		portfolio.addStock(stock3);
		
		
		return portfolio;
	}

}
