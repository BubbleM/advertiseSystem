package com.myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mybean.data.Login;
import com.mybean.data.Password;

public class HandlePassword extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HandlePassword() {
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
		 Login login=(Login)session.getAttribute("login"); //��ȡ�û���¼ʱ��Javabean
		 boolean ok=true;
		 if(login==null){
			 ok=false;
			 response.sendRedirect("login.jsp"); //�ض��򵽵�¼ҳ��
		 }
		 if(ok==true){ 
			 continueWork(request,response);
		 }
	}
	
	 public void continueWork(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		 HttpSession session=request.getSession(true);
			 Login login=(Login)session.getAttribute("login");
			 Connection con=null;
			 String logname=login.getLogname();
			 Password passwordBean=new Password();
			 request.setAttribute("password",passwordBean);
			 String oldPassword=request.getParameter("oldPassword");
			 String newPassword=request.getParameter("newPassword");
			 String
			uri="jdbc:mysql://localhost:3306/e-commerce?useUnicode=true&characterEncoding=utf-8";
			 try{ con=DriverManager.getConnection(uri,"root","123456");
			 Statement sql=con.createStatement();
			 ResultSet rs=
			 sql.executeQuery("SELECT * FROM member where logname='"+
			logname+"'And password='"+oldPassword+"'");
			 if(rs.next())
			 { String updateString="UPDATE member SET password='"+
			newPassword+"' where logname='"+logname+"'";
			 int m=sql.executeUpdate(updateString);
			 if(m==1)
			 { passwordBean.setBackNews("������³ɹ�");
			 passwordBean.setOldPassword(oldPassword);
			 passwordBean.setNewPassword(newPassword);
			 }
			 else
			 { passwordBean.setBackNews("�������ʧ��");
			 }
			 }
			 else
			 { passwordBean.setBackNews("�������ʧ��");
			 }
			 }
			 catch(SQLException exp)
			 { passwordBean.setBackNews("�������ʧ��"+exp);
			 }
			 RequestDispatcher dispatcher=
			request.getRequestDispatcher("showNewPassword.jsp");//ת��
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
		}catch(Exception e){}
	 }
}
