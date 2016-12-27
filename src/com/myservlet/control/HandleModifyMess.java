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
import com.mybean.data.ModifyMessage;

public class HandleModifyMess extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HandleModifyMess() {
		super();
	}

	 public String handleString(String s)
	  { try{ byte bb[]=s.getBytes("utf-8");
	  s=new String(bb);
	  }
	  catch(Exception ee){}
	  return s;
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
		 { continueDoPost(request,response);
		 }
	}
	
	public void continueDoPost(HttpServletRequest request,
			HttpServletResponse response)
			 throws ServletException,IOException
			 { HttpSession session=request.getSession(true);
			 Login login=(Login)session.getAttribute("login");
			 String logname=login.getLogname();
			 Connection con;
			 PreparedStatement sql;
			 ModifyMessage modify=new ModifyMessage();
			 request.setAttribute("modify",modify);
			 String advertiseTitle=request.getParameter("newAdvertiseTitle").trim(),
			 email=request.getParameter("newEmail").trim(),
			 phone=request.getParameter("newPhone").trim(),
			 message=request.getParameter("newMessage");
			 String
			uri="jdbc:mysql://localhost:3306/e-commerce?useUnicode=true&characterEncoding=utf-8";
			 String backNews="";
			 try{ con=DriverManager.getConnection(uri,"root","123456");
			 String updateCondition=
			 "UPDATE member SET advertiseTitle=?,phone=?,email=?,message=? WHERE logname=?";
			 sql=con.prepareStatement(updateCondition);
			 sql.setString(1,handleString(advertiseTitle));
			 sql.setString(2,phone);
			 sql.setString(3,handleString(email));
			 sql.setString(4,handleString(message));
			 sql.setString(5,logname);
			 int m=sql.executeUpdate();
			 if(m==1)
			 { backNews="修改信息成功";
			 modify.setBackNews(backNews);
			 modify.setLogname(logname);
			 modify.setNewAdvertiseTitle (handleString(advertiseTitle));
			 modify.setNewEmail(handleString(email));
			 modify.setNewPhone(phone);
			 modify.setNewMessage(handleString(message));
			 }
			 else
			 { backNews="信息填写不完整或信息中有非法字符";
			 modify.setBackNews(backNews);
			 }
			 con.close();
			 }
			 catch(SQLException exp)
			 { modify.setBackNews(""+exp);
			 }
			 RequestDispatcher dispatcher=
			request.getRequestDispatcher("showModifyMess.jsp");//转发
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
