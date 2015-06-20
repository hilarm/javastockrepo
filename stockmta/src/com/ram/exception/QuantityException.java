package com.ram.exception;

import org.algo.exception.PortfolioException;

public class QuantityException extends PortfolioException {
	public QuantityException(){
		super("Quantity cant be be negative");
	}
	public QuantityException(int quantity){
		super("Quantity: " + quantity+ " is incorrect");
	}

}
