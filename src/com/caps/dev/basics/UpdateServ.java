package com.caps.dev.basics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServ extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		long phone = Long.parseLong(req.getParameter("number"));
		String old_pass = req.getParameter("pass1");
		String new_pass = req.getParameter("pass2");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/capsjava_db";
			con = DriverManager.getConnection(dburl,"root","root");
			String query = "Update caps_table set password = ? where phone_number = ? and password = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,new_pass);
			pstmt.setLong(2,phone);
			pstmt.setString(3,old_pass);
			
			int count = pstmt.executeUpdate();
			
			if(count>0)
			{
				out.print("<h1>"+"Password Updated"+"</h1>");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			out.print("<h1>"+"Something went wrong"+"</h1>");
		}finally
		{
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
