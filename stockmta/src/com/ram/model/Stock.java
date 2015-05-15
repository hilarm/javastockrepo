/**
 * The class represent a stock where all the stocks data is gather.
 * @author: Hila Ram
 * Last change date May, 7. 
 */
package com.ram.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

import com.ram.model.Portfolio.ALGO_RECOMMENDATION;

public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	//constructor
	public Stock(String symbol, float ask, float bid,Date date2,int quantity) {
		this.symbol=symbol;
		this.ask=ask;
		this.bid=bid;
		this.date=date2;
		this.stockQuantity=quantity;
	}
	//copy constructor
	public Stock(Stock stck) {
		this(stck.getSymbol(),stck.getAsk(),stck.getBid(),new Date( stck.getDate().getTime()),stck.getStockQuantity());
	}
	
	//getters and setters
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	/**print stock's description (symbol, ask, bid, date,quantity)
	 * @return string
	 */
	public String getHtmlDescription(){
		DateFormat dateStr = new SimpleDateFormat("MM/dd/yyyy");
		String result = new String("<b>Stock symbol</b>: "+this.getSymbol()+" <b>Bid</b>: "+this.getBid()+" <b>Ask</b>: "+this.getAsk()+" <b>Date</b>: "+dateStr.format(getDate())+" <b>Quantity</b>: "+this.getStockQuantity());
		return result;
	}
}


