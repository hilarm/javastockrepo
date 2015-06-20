/**
 * exception to be thrown when the portfolio balance becomes negative
 * @author: Hila Ram
 */
package com.ram.exception;

import javax.lang.model.type.ErrorType;

import org.algo.exception.PortfolioException;

public class BalanceException extends PortfolioException {
	public BalanceException(){
			super("Not enough money to purchase");
	}
	public BalanceException(String errorMsg){
		super("Balance cant be negative");
	}

}
