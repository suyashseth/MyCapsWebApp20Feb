package com.caps.dev.basics;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryServ extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String value = req.getParameter("fname");
		System.out.println(value);
		String value2 = req.getParameter("lname");
		PrintWriter out = resp.getWriter();
		out.print("<h1>"+value+"</h1>");
		out.print("<h2>"+value2+"</h2>");
		
		
		
		
		
		
		
		
		
	}
}
