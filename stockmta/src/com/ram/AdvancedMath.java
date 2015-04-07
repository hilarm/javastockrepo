package com.ram;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdvancedMath extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		resp.setContentType("text/html");
		//question 1
		int radius = 50;
		double area = radius*radius*Math.PI;
		String line1 = new String ("<h1>1. Area of circle with radius "+radius+" is "+area+" square-cm</h>");
		//question 2
		int bAngle=30;
		int hypotenuse = 50;
		double bAngleRadian = Math.toRadians(bAngle);
		double opposite = Math.sin(bAngleRadian)*hypotenuse;
		String line2 = new String ("<h1>2. Length of opposite where angle B is "+bAngle+" degrees and Hypotenuse length is "+hypotenuse+" cm is "+opposite+" cm</h>");
		//question 3
		int base=20;
		int exp=13;
		long result =  (long) Math.pow(base,exp);
		String line3= new String ("<h1>3. Power of "+base+" with exp of "+exp+" is "+result+"</h>");
		String resultStr = line1+"<br>"+line2+"<br>"+line3;
		resp.getWriter().println( resultStr);
		
		
	}
}
