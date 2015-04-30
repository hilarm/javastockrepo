/*
 * Stock - holds all stock data (symbol,bid,ask,date) and print them. 
 */
package com.ram;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;
	private final static int BUY=0;
	private final static int SELL=1;
	private final static int REMOVE=2;
	private final static int HOLD=3;
	public Stock(String symbol, float ask, float bid,Date date2) {
		this.symbol=symbol;
		this.ask=ask;
		this.bid=bid;
		this.date=date2;
	}
	public Stock(Stock stck) {
		this(stck.getSymbol(),stck.getAsk(),stck.getBid(), stck.getDate());
	}
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
	public String getHtmlDescription(){
		DateFormat dateStr = new SimpleDateFormat("MM/dd/yyyy");
		String result = new String("<b>Stock symbol</b>: "+getSymbol()+" <b>Bid</b>: "+getBid()+" <b>Ask</b>: "+getAsk()+" <b>Date</b>: "+dateStr.format(getDate()));
		return result;
	}
}


