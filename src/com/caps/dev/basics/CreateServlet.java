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

public class CreateServlet extends HttpServlet{ 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String numb = req.getParameter("num");
		String password = req.getParameter("pass");
		String gender = req.getParameter("gender");

		long number = Long.parseLong(numb);
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/capsjava_db?user=root&password=root";
			con = DriverManager.getConnection(dburl);
            System.out.println("cONNECTOED");
			String query = "insert into caps_table values(?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,name);
			pstmt.setString(2,email);
			pstmt.setLong(3,number);
			pstmt.setString(4,password);
			pstmt.setString(5,gender);

			int count = pstmt.executeUpdate();
			if(count>0)
			{
				out.print("<h1>"+"Data Successfully added"+"</h1>");
				
			}
			else
			{
				out.print("<h1>"+"Something went wrong"+"</h1>");
			}


		}catch(Exception e)
		{
			e.printStackTrace();
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
