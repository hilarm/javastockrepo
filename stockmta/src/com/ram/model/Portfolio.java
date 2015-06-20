/**
 * The class represent a portfolio where all the stocks are  gather.
 * @author: Hila Ram 
 */
package com.ram.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import sun.org.mozilla.javascript.internal.ast.ThrowStatement;

import com.ram.exception.BalanceException;
import com.ram.exception.PortfolioFullException;
import com.ram.exception.QuantityException;
import com.ram.exception.StockAlreadyExistsException;
import com.ram.exception.StockNotExistException;


public class Portfolio implements PortfolioInterface {
	public enum ALGO_RECOMMENDATION {
		BUY,SELL, REMOVE,HOLD
	}
	public static final int MAX_PORTFOLIO_SIZE=5;
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
	//array constructor
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
	 * @throws BalanceException
	 */
	public void updateBalance (float amount)throws BalanceException {
		float result=this.balance+(amount);
		if(result < 0){
			throw new BalanceException("Balance can't be negative");
		}else{
			this.balance=result;
		}
	}
	/**
	 * Add new stock to portfolio using stock array. 
	 *@param stock, the stock to add to portfolio
	 *@throws PortfolioFullException
	 *@throws StockAlreadyExistsException
	 *@throws StockNotExistException
	 */

	public void addStock(Stock stock) throws PortfolioFullException, StockAlreadyExistsException, StockNotExistException  {
		boolean flag = false; //check if stock exist
		
		if(this.stockIndex == MAX_PORTFOLIO_SIZE){  
			System.out.println("Can’t add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +" stocks");
			throw new PortfolioFullException();
		}
		else if (stock==null){
			throw new StockAlreadyExistsException("The stock is null");
		}
		
		for(int i=0 ; i<this.stockIndex; i++){ 
			if(stock.getSymbol().equals(stocks[i].getSymbol())){
				flag = true;
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
		}
		if((flag == false) && (stock != null)){    
			this.stocks[stockIndex] = new Stock(stock);
			this.stockIndex++;
		}else{
			System.out.println("stock is null or exists in array");
		}
	}

	/**
	 *Remove stock from portfolio using symbol.
	 *@param symbol
	 *@return boolean
	 *@throws StockNotExistException
	 * @throws QuantityException 
	 * @throws BalanceException 
	 */
	public void removeStock(String symbol)throws StockNotExistException, BalanceException, QuantityException{
		boolean flag=false; //check for sell
		int symbolIndex=0;
		if (symbol==null){
			throw new StockNotExistException();
		}
		//find if stock exist in array
		for(int i=0;i<stockIndex;i++){
			if(stocks[i].getSymbol().equals(symbol)){
				flag=true;
				symbolIndex=i;
			}
		}
		if(flag==false){
			throw new StockNotExistException(symbol);
		}
		if(flag==true){
			sellStock(symbol, -1);
			if(stocks[symbolIndex].equals(symbol)){
				this.stocks[symbolIndex]=null;
				this.stockIndex--;
			}else{
				this.stocks[symbolIndex]=this.stocks[stockIndex-1];
				stocks[stockIndex-1]=null;
				this.stockIndex--;
			}
		}
	}
	
	/**
	 * sell stock, not removing it from array
	 * @param symbol.
	 * @param quantity.
	 * @return boolean
	 * @throws StockNotExistException
	 * @throws BalanceException 
	 * @throws QuantityException 
	 */
	public void sellStock(String symbol,int quantity) throws StockNotExistException, BalanceException, QuantityException {
		float sellValue=0;
		for (int i=0;i<stockIndex;i++){
			if(stocks[i].getSymbol().equals(symbol)){
				if(quantity==-1){ //sell all units
					sellValue=stocks[i].getBid()*((Stock) stocks[i]).getStockQuantity();
					updateBalance(sellValue);
					((Stock) stocks[i]).setStockQuantity(0);
				}else if(quantity<0){ //check for typo, negative values
					throw new QuantityException();
				}else if(((Stock) stocks[i]).getStockQuantity()<quantity){ //error - sell more units than it has 
					throw new QuantityException(quantity);
				}else{//regular case
					sellValue=stocks[i].getBid()*quantity;
					updateBalance(sellValue);
					((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()-quantity);
				}
			}
		}
	}
	
	/**
	 * buy stock
	 * @param stock
	 * @param quantity
	 * @return boolean
	 * @throws BalanceException
	 * @throws StockAlreadyExistsException 
	 * @throws StockNotExistException 
	 * @throws PortfolioFullException 
	 * @throws QuantityException 
	 */
	public void buyStock (Stock stock,int quantity)throws BalanceException, PortfolioFullException, StockNotExistException, StockAlreadyExistsException, QuantityException{
		float buyValue=0;
		//boolean flag;//check if exist
		if(stockIndex==0){
			addStock(stock);
		}
		if(quantity*stock.getAsk()>this.balance){
			throw new BalanceException();
		}
		if(quantity<0){
			throw new QuantityException();
		}
		for(int i=0 ; i<stockIndex ; i++){
			if(stocks[i].getSymbol().equals(stock.getSymbol())){
				
				if(quantity == -1){
					buyValue=stock.getAsk()*stock.getStockQuantity()*(-1);
					if(buyValue>balance){
						removeStock(stock.getSymbol());
						throw new BalanceException();
					}else{
						updateBalance(getBalance()-buyValue);
						((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()+quantity);
					}					
				}else{
					buyValue=stock.getAsk()*quantity*(-1);
					updateBalance(buyValue);
					((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()+quantity);
				}
			}else if(stocks[i+1]==null){
				addStock(stock);
			}
		}
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
	/**
	 * 
	 * @param symbol
	 * @return
	 * @throws StockNotExistException
	 */

	public StockInterface findStock(String symbol) throws StockNotExistException{
		for(int i=0 ; i<this.stockIndex ; i++){
			if(this.stocks[i].getSymbol().equals(symbol)){
				return stocks[i];
			}
		}
		throw new StockNotExistException();
	}
	

}
