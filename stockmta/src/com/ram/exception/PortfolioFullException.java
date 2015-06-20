/**
 * exception to be thrown when adding more stocks than max stocks limitation
 * @author: Hila Ram
 */
package com.ram.exception;

import org.algo.exception.PortfolioException;

public class PortfolioFullException extends PortfolioException {
	public PortfolioFullException(){
		super("you have reached max size");
	}
}
