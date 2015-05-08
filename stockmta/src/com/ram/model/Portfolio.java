/*
 * The class represent a portfolio where all the stocks are  gather.
 * @author: Hila Ram
 * Last change date May, 7. 
 */
package com.ram.model;


public class Portfolio {
	private String title;
	private static final int MAX_PORTFOLIO_SIZE=5;
	private int stockIndex;
	Stock[] stocks;
	Stock current;
	
	//empty constructor
	public Portfolio (){
		this.title="Portfolio";
		stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.stockIndex=0;
	}
	//constructor
	public Portfolio(String title, Stock[] stocks, int stockIndex){
		title=this.title;
		stocks=new Stock [MAX_PORTFOLIO_SIZE];
		stockIndex=0;
	}
	//copy constructor
	public Portfolio(Portfolio port){
		this.title=port.title;
		for(int i=0 ; i<MAX_PORTFOLIO_SIZE;i++){
			stocks[i] = new Stock (port.stocks[i]);
		}
		this.stockIndex=port.stockIndex;
	}
	/*Add new stock to portfolio using stock array. 
	 *Doesn't have a return value
	 */
	public void addStock(Stock stock){
		if(stock !=null && stockIndex<MAX_PORTFOLIO_SIZE){
			this.stocks[stockIndex]=stock;
			stockIndex++;
		}else {
			System.out.println("Sorry, stocks is full, or stock is null!");
		}
	}
	/*Remove stock from portfolio using delIndex.
	 *Doesn't have a return value
	 */
	public void removeStock(int delIndex){
		for (int i=delIndex ; i<stockIndex; i++){
				this.stocks[i]=this.stocks[i+1];
			}stockIndex--;
		}
	
	/*Change a bid of a stock using bidIndex.
	 *Doesn't have a return value
	 */
	public void changeBid(float bid, int bidIndex){
			stocks[bidIndex].setBid(bid);
		}
	
	//getters and setters	
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
	/*print a portfolio using stock class method
	 *return value is a string type
	 */
	
	public String getHtmlString	(){
		String result = new String ("<h1>"+getTitle()+"</h1>");
		for (int i=0 ; i<stockIndex;i++){
			current=this.stocks[i];
			result+=current.getHtmlDescription()+"<br>";		
		}
		return result;
	}
	

}
