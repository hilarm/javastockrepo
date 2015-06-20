/**
 * exception to be thrown when a stock already exists.
 * @author: Hila Ram
 */
package com.ram.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException {
	public StockAlreadyExistsException(String symbol) {
		super("stock " + symbol + " already exists");
	}
	
}
