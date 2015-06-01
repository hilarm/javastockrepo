/**
 * The class represent a portfolio where all the stocks are  gather.
 * @author: Hila Ram
 * Last change date May, 7. 
 */
package com.ram.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;


public class Portfolio implements PortfolioInterface {
	public enum ALGO_RECOMMENDATION {
		BUY,SELL, REMOVE,HOLD
	}
	private static final int MAX_PORTFOLIO_SIZE=5;
	private String title;
	private int stockIndex; // logic size
	private StockInterface[] stocks;
	private Stock current;
	private float balance;
	
	//empty constructor
	public Portfolio (){
		this.title=null;
		this.stocks= new Stock[MAX_PORTFOLIO_SIZE];
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
		this(port.getTitle(), null, port.getStockIndex(), port.getBalance());
		StockInterface[] coppied = port.getStocks();
		for(int i=0;i<coppied.length;i++){
			this.stocks[i]=new Stock(coppied[i]);
		}
	}
	//array c'tor
	public Portfolio(Stock[] stockArray) {
		this();
		this.stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.stockIndex=0;
		this.title=null;
		this.balance=0;
		for(int i=0 ; i<stockArray.length;i++){
			this.stocks[i]=stockArray[i];
			this.stockIndex++;
		}
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
			this.balance=result;
		}
		
	}
	/**
	 * Add new stock to portfolio using stock array. 
	 *@param stock, the stock to add to portfolio
	 */
	
	
public void addStock(Stock stock){
		boolean flag = false;
		if(this.stockIndex == MAX_PORTFOLIO_SIZE){   // #1 - portfolio or the array is full
			System.out.println("Can’t add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			return;
		}
		
		for(int i=0 ; i<this.stockIndex; i++){         // #2 - the stock exists in array already
			if(stock.getSymbol().equals(stocks[i].getSymbol())){
				flag = true;
				System.out.println("the stock is already exist");
				return;
			}
		}
		if((flag == false) && (stock != null)){    // case #3 - NOT exists and NOT null - it's case o.k
			this.stocks[stockIndex] = new Stock(stock);
			this.stockIndex++;
		}else{
			System.out.println("the stock is null or the stock exists already in array");
		}
	}
	/*	public void addStock(Stock stock){
		int i=0;
		boolean flag=false;
		if(stockIndex==MAX_PORTFOLIO_SIZE){
			System.out.println("Can't add new stock, protfolio can have only"+MAX_PORTFOLIO_SIZE+"stocks");
		}else if(stockIndex==0){
			this.stocks[stockIndex]=stock;
			stockIndex++;
		
		}else{	
		for(i=0; i<stockIndex;i++){
			if(stocks[i].getSymbol().equals(stock.getSymbol())){
				System.out.println("The stock already exist");
				break;
			}
			else if((flag==false) && (stock!=null)){         // stock != null && flag == false
				this.stocks[stockIndex]=new Stock(stock);
				this.stockIndex++;
				flag = true;
				break;
			}else{
				System.out.println("the stock is null or the stock exists already in array");
					
				}
	
		}
		}
		}*/
	
	
	
		
	

	
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
					sellValue=stocks[i].getBid()*((Stock) stocks[i]).getStockQuantity();
					updateBalance(sellValue);
					((Stock) stocks[i]).setStockQuantity(0);
					return true;
				}else if(quantity<0){ //check for typo, negative values
					System.out.println("Error");
				}else if(((Stock) stocks[i]).getStockQuantity()<quantity){ //error - sell more units than it has 
					System.out.println("Not enough stocks to sell");
				}else{//regular case
					sellValue=stocks[i].getBid()*quantity;
					updateBalance(sellValue);
					((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()-quantity);
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
						((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()+quantity);
						return true;
					}					
				}else{
					buyValue=stock.getAsk()*quantity*(-1);
					updateBalance(buyValue);
					((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()+quantity);
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
			total=total+(stocks[i].getBid()*((Stock) stocks[i]).getStockQuantity());
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
			((Stock) stocks[bidIndex]).setBid(bid);
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
		this.stocks = (StockInterface[]) stocks;
	}

	public StockInterface[] getStocks (){
		return (StockInterface[]) stocks;	
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
			current=(Stock) this.stocks[i];
			result+="<br>"+current.getHtmlDescription();		
		}
		return result;
	}

	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	public StockInterface findStock(String symbol) {
		for(int i=0 ; i<this.stockIndex ; i++){
			if(this.stocks[i].getSymbol().equals(symbol)){
				return stocks[i];
			}
		}
		return null;
	}
	

}
