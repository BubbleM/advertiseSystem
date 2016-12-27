package com.myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mybean.data.Register;

public class HandleRegister extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HandleRegister() {
		super();
	}
	
	public String handleString(String s){
		try{
			byte bb[] = s.getBytes("utf-8");
			s = new String(bb);
		}catch(Exception ee){
			
		}
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
		DataSource ds = null;
		Context env = null;
		Connection con = null;
		PreparedStatement sql;  //PreparedStatement 继承于 Statement
		Register reg = new Register(); //定义一个新的 register 对象
		request.setAttribute("register",reg); //给 request 增加了一个属性
		
		String logname = request.getParameter("logname").trim(),
		password = request.getParameter("password").trim(),
		advertiseTitle=request.getParameter("advertiseTitle").trim(),
		email=request.getParameter("email").trim(),
		phone=request.getParameter("phone").trim(),
		message=request.getParameter("message"); //从 request 中提取用户提交的信息
		
		if(logname == null){
			logname = "";
		}
		if(password == null){
			password = "";
		}
		
		boolean isLD = true;
		for(int i=0;i<logname.length();i++){
			char c = logname.charAt(i);
			if(!((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0')))
				 isLD=false;
		}
		
		boolean boo=logname.length()>0&&password.length()>0&&isLD; 
		String backNews="";
		try{
			 System.out.println(System.getProperty("java.naming.factory.initial"));
			 try{
				 env = (Context) new InitialContext().lookup("java:comp/env");
				 ds = (DataSource)env.lookup("jdbc/mysql");
				 con = ds.getConnection();
			 }catch (Exception e){
				 e.printStackTrace();
			 }
			 
			 String insertCondition="INSERT INTO member VALUES (?,?,?,?,?,?,?)";
		
			 sql=con.prepareStatement(insertCondition);
			 System.out.println(logname);
			 System.out.println(password);
			 System.out.println(advertiseTitle);
			 if(boo){ 
				 sql.setString(1,handleString(logname));
				 sql.setString(2,handleString(password));
				 sql.setString(3,handleString(advertiseTitle));
				 sql.setString(4,phone);
				 sql.setString(5,email);
				 sql.setString(6,handleString(message));
				 sql.setString(7,"public.jpg");
				 System.out.println(insertCondition);
				 System.out.println("boo"+boo);
				 
				 int m=sql.executeUpdate();
				 if(m!=0){ 
					 backNews="注册成功";
					 reg.setBackNews(backNews);
					 reg.setLogname(logname);
					 reg.setPassword(handleString(password));
					 reg.setAdvertiseTitle(handleString(advertiseTitle));
					 reg.setEmail(handleString(email));
					 reg.setPhone(phone);
					 reg.setMessage(handleString(message));
				 }
			}else{ 
				backNews="信息填写不完整或名字中有非法字符"; //当 logname 信息不
				reg.setBackNews(backNews);
			 }
			con.close();
		 }catch(SQLException exp){ 
			 backNews="该会员名已被使用，请您更换名字"+exp;
			 reg.setBackNews(backNews);
		  }
			 RequestDispatcher dispatcher= request.getRequestDispatcher("showRegisterMess.jsp");//转发
			 dispatcher.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
	}

}
