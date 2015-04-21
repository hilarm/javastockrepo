package com.ram;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		resp. setContentType("text/html");
		//define variables
		Calendar cal=Calendar.getInstance();
		cal.set(2014,10,15);
		Date date1=cal.getTime();
		cal.set(2014,10,15);
		Date date2=cal.getTime();
		cal.set(2014, 10,15);
		Date date3=cal.getTime();
		Stock stock1=new Stock("PIH" , 13.1f, 12.4f,date1);
		Stock stock2=new Stock("AAL", 5.78f, 5.5f ,date2);
		Stock stock3=new Stock("CAAS", 32.2f, 31.5f,date3 );
		//print
		String line1=stock1.getHtmlDescription();
		String line2=stock2.getHtmlDescription();
		String line3=stock3.getHtmlDescription();
		String resultStr = line1+"<br>"+line2+"<br>"+line3;
		resp.getWriter().println(resultStr);
	}

}
