package com.myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mybean.data.Login;
import com.mybean.data.Register;

public class GetOldMess extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetOldMess() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		 Login login=(Login)session.getAttribute("login");//获取用户登录时的Javabean
		 boolean ok=true;
		 if(login==null)
		 { ok=false;
		 response.sendRedirect("login.jsp"); //重定向到登录页面
		 }
		 if(ok==true)
		 { continueWork(request,response);
		 }
	}

	public void continueWork(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException,IOException
			 { HttpSession session=request.getSession(true);
			 Login login=(Login)session.getAttribute("login");
			 Connection con=null;
			 String logname=login.getLogname();
			 Register register=new Register();
			 request.setAttribute("register",register);
			 String
			uri="jdbc:mysql://localhost:3306/e-commerce?useUnicode=true&characterEncoding=utf-8";
			 try{ con=DriverManager.getConnection(uri,"root","123456");
			 Statement sql=con.createStatement();
			 ResultSet rs=
			 sql.executeQuery("SELECT * FROM member where logname='"+logname+"'");
			 if(rs.next())
			 { register.setLogname(rs.getString(1));
			 register.setAdvertiseTitle(rs.getString(3));
			 register.setPhone(rs.getString(4));
			 register.setEmail(rs.getString(5));
			 register.setMessage(rs.getString(6));
			 register.setBackNews("您原来的注册信息:");
			 }
			 }
			 catch(SQLException exp)
			 { register.setBackNews(""+exp);
			 }
			 RequestDispatcher dispatcher=
			request.getRequestDispatcher("inputModifyMess.jsp");//转发
			 dispatcher.forward(request, response);
			 } 
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	 public void init(ServletConfig config) throws ServletException{
		 super.init(config);
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
		 }catch(Exception e){
			 
		 }
	 }

}
