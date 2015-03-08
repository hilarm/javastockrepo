package com.ram;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockmtaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {//ff
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
