/**
 * exception to be thrown when a stock doesn’t exist.
 * @author: Hila Ram
 */
package com.ram.exception;

import org.algo.exception.PortfolioException;

public class StockNotExistException extends PortfolioException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotExistException(String symbol) {
		super("Stock: " + symbol + " doesn't exist in portfolio");
	}

	public StockNotExistException() {
		super("Stock is null");
	}
}
