/*
 * portfolio - manage all stocks in one place. 
 */
package com.ram.model;

import com.ram.Stock;

public class Portfolio {
	private String title;
	private static final int MAX_PORTFOLIO_SIZE=5;
	private int stockIndex;
	Stock[] stocks;
	Stock current;
	public Portfolio (){
		this.title="Portfolio";
		stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.stockIndex=0;
	}

	public void addStock(Stock stock){
		if(stock !=null && stockIndex<MAX_PORTFOLIO_SIZE){
			this.stocks[stockIndex]=stock;
			stockIndex++;
		}else {
			System.out.println("Sorry, stocks is full, or stock is null!");
		}
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStockIndex() {
		return stockIndex;
	}

	public void setStockIndex(int stockIndex) {
		this.stockIndex = stockIndex;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public Stock[] getStocks (){
		return stocks;	
	}
	public String getHtmlString	(){
		String result = new String ("<h1>"+getTitle()+"</h1>");
		for (int i=0 ; i<stockIndex;i++){
			current=this.stocks[i];
			result+=current.getHtmlDescription()+"<br>";		
		}
		return result;
	}
	

}
