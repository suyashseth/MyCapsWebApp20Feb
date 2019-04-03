package com.caps.dev.basics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServ extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String name = req.getParameter("name");
		String pass = req.getParameter("password");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/capsjava_db";
			con = DriverManager.getConnection(dburl,"root","root");

			String query = "select * from caps_table where name = ? and password = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,name);
			pstmt.setString(2,pass);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				long num = rs.getInt("phone_number");
				String user = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String gender = rs.getString("gender");

				out.print("<h2>"+user+"</h2>");
				out.print("<h2>"+email+"</h2>");
				out.print("<h2>"+password+"</h2>");
				out.print("<h2>"+num+"</h2>");
				out.print("<h2>"+gender+"</h2>");

			}
			else
			{
				out.print("<h1>"+"Given Entity is not present"+"</h1>");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			out.print("<h1>"+"something went wrong"+"</h1>");
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
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			
			
			
			

		}




	}
}
