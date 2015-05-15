/**
 * The class represent a portfolio where all the stocks are  gather.
 * @author: Hila Ram
 * Last change date May, 7. 
 */
package com.ram.model;


public class Portfolio {
	public enum ALGO_RECOMMENDATION {
		BUY,SELL, REMOVE,HOLD
	}
	private String title;
	private static final int MAX_PORTFOLIO_SIZE=5;
	private int stockIndex; // logic size
	Stock[] stocks;
	Stock current;
	private float balance;
	
	//empty constructor
	public Portfolio (){
		this.title="Portfolio";
		stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.stockIndex=0;
		this.balance=0;
	}
	
	//constructor
	public Portfolio(String title, Stock[] stocks, int stockIndex,float balance){
		title=this.title;
		stocks=new Stock [MAX_PORTFOLIO_SIZE];
		stockIndex=this.stockIndex;
		balance=this.balance;			
	}
	//copy constructor
	public Portfolio(Portfolio port){
		this.title=port.title;
		for(int i=0 ; i<MAX_PORTFOLIO_SIZE;i++){
			stocks[i] = new Stock (port.stocks[i]);
		}
		this.stockIndex=port.stockIndex;
		this.balance=port.balance;
		
	}
	/**
	 * Balance is the available money for investment.
	 * @param amount 
	 */
	public void updateBalance (float amount){
		this.balance=getBalance();
		float result=balance+amount;
		if(result < 0){
			System.out.println("Balance can't be negative");
		}else{
			balance=result;
		}
		
	}
	/**
	 * Add new stock to portfolio using stock array. 
	 *@param stock, the stock to add to portfolio
	 */
	public void addStock(Stock stock){
		int i=0;
		if(stockIndex==MAX_PORTFOLIO_SIZE){
			System.out.println("Can't add new stock, protfolio can have only"+MAX_PORTFOLIO_SIZE+"stocks");
		}
		if(stocks[stockIndex]==null){
			this.stocks[stockIndex]=stock;
			stockIndex++;
		}else{
		for(i=0; i<stockIndex ; i++){
			if(stocks[i].getSymbol().equals(stock.getSymbol())){
				break;
			}
		}
	}
	}
	
	/**
	 *Remove stock from portfolio using symbol.
	 *@param symbol
	 *@return boolean
	 */
	public boolean removeStock(String symbol){
		boolean flag; //check for sell
		for (int i=0 ;i<stockIndex ; i++){
			if(this.stocks[i].getSymbol().equals(symbol)){
				flag=sellStock(symbol, -1);//stop
				if (flag==true){
					if(stocks[i].getSymbol().equals(symbol)){
						this.stocks[i]=null;
						this.stockIndex--;
						return true;
					}else{
						this.stocks[i]=this.stocks[stockIndex-1];
						stocks[stockIndex-1]=null;
						stockIndex--;
						return true;
						
					}
				}
		}
		}return false;
	}
	/**
	 * sell stock, not removing it from array
	 * @param symbol.
	 * @param quantity.
	 * @return boolean
	 */
	public boolean sellStock(String symbol,int quantity) {
		float sellValue=0;
		for (int i=0;i<stockIndex;i++){
			if(stocks[i].getSymbol().equals(symbol)){
				if(quantity==-1){ //sell all units
					sellValue=stocks[i].getBid()*stocks[i].getStockQuantity();
					updateBalance(sellValue);
					stocks[i].setStockQuantity(0);
					return true;
				}else if(quantity<0){ //check for typo, negative values
					System.out.println("Error");
				}else if(stocks[i].getStockQuantity()<quantity){ //error - sell more units than it has 
					System.out.println("Not enough stocks to sell");
				}else{//regular case
					sellValue=stocks[i].getBid()*quantity;
					updateBalance(sellValue);
					stocks[i].setStockQuantity(stocks[i].getStockQuantity()-quantity);
					return true;
				}
			}
		}return false;
	}
	/**
	 * buy stock
	 * @param stock
	 * @param quantity
	 * @return boolean
	 */
	public boolean buyStock (Stock stock,int quantity){
		float buyValue=0;
		boolean flag;//check for remove
		if(stockIndex==0){
			addStock(stock);
		}
		for(int i=0 ; i<stockIndex ; i++){
			if(stocks[i].getSymbol().equals(stock.getSymbol())){
				if(quantity == -1){
					buyValue=stock.getAsk()*stock.getStockQuantity()*(-1);
					if(buyValue>balance){
						System.out.println("Not enough balance to complete purchase");
						flag=removeStock(stock.getSymbol());
						if(flag ==true){
							return false;
						}else{
							System.out.println("Error acurred while removing, please check your input");
						}
					}else{
						updateBalance(getBalance()-buyValue);
						stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
						return true;
					}					
				}else{
					buyValue=stock.getAsk()*quantity*(-1);
					updateBalance(buyValue);
					stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
					return true;
				}
			}else if(stocks[i+1]==null){
				addStock(stock);
			}
		}return false;
	}
	
	/**
	 * The invested money.
	 * @param stocks
	 * @return float total stocks value
	 */
	public float getStockValue(){
		float total=0;
		for(int i=0;i<stockIndex;i++){
			total=total+(stocks[i].getBid()*stocks[i].getStockQuantity());
		}return total;
	}
	
	/**
	 * The value of portfolio (invested money and not invested money)
	 * @param totalValue
	 * @return float total value
	 */
	public float getTotalValue(){
		float totalValue = 0;
		totalValue=getStockValue()+getBalance();
		return totalValue;
	}
	
	/**
	 * change bid value of stock
	 * @param bid value
	 * @param bidIndex
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
	public float getBalance() {
		return balance;
	}

	/**print a portfolio using stock class method
	 *@return print portfolio
	 */
	
	public String getHtmlString	(){
		String result=new String ("<h1>"+getTitle()+"</h1>"+ "<b>Total portfolio value: </b>"+this.getTotalValue()+"$"+"<b> Total stock value: </b>"+this.getStockValue()+"$"+"<b> Balance: </b>"+this.getBalance()+"$"+"<br>");
		for (int i=0 ; i<this.stockIndex;i++){
			current=this.stocks[i];
			result+="<br>"+current.getHtmlDescription();		
		}
		return result;
	}
	

}
