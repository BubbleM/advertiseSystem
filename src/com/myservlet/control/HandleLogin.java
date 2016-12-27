package com.myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mybean.data.Login;

public class HandleLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public HandleLogin() {
		super();
	}
	
	public String handleString(String s){ 
		try{ 
			byte bb[]=s.getBytes("utf-8");
			s=new String(bb);
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
		 Connection con;
		 PreparedStatement sql;
		 Login loginBean=null;
		 String backNews="";
		 HttpSession session=request.getSession(true);
		 try{ 
			 loginBean=(Login)session.getAttribute("login");
			 if(loginBean==null){ 
				 loginBean=new Login();
				 session.setAttribute("login",loginBean);
			 }
		 }catch(Exception ee){ 
			 loginBean=new Login();
			 session.setAttribute("login",loginBean);
		 }
		 String logname=request.getParameter("logname").trim(),
		 password=request.getParameter("password").trim();
	
		 boolean ok=loginBean.getSuccess();
		 logname=handleString(logname);
		 password=handleString(password);
		 if(ok==true&&logname.equals(loginBean.getLogname())){ 
			 backNews=logname+"已经登录了";
			 loginBean.setBackNews(backNews);
		 }else{ 
			 String uri="jdbc:mysql://localhost:3306/e-commerce?useUnicode=true&characterEncoding=utf-8";
			 boolean boo=(logname.length()>0)&&(password.length()>0);
			 try{ 
				 con=DriverManager.getConnection(uri,"root","123456");
				 String condition="select * from member where logname =? and password =?";
				 sql=con.prepareStatement(condition);
				 if(boo){ 
					 sql.setString(1,logname);
					 sql.setString(2,password);
					 ResultSet rs=sql.executeQuery();
					 boolean m=rs.next();
					 if(m==true){ 
						 backNews="登录成功";
						 loginBean.setBackNews(backNews);
						 loginBean.setSuccess(true);
						 loginBean.setLogname(logname);
					 }else{ 
						 backNews="您输入的用户名不存在，或密码不般配";
						 loginBean.setBackNews(backNews);
						 loginBean.setSuccess(false);
						 loginBean.setLogname(logname);
						 loginBean.setPassword(password);
					}
				 }else{ 
					 backNews="您输入的用户名不存在，或密码不般配";
					 loginBean.setBackNews(backNews);
					 loginBean.setSuccess(false);
					 loginBean.setLogname(logname);
					 loginBean.setPassword(password);
				 	}
				 con.close();
			 	}catch(SQLException exp){ 
			 		backNews=""+exp;
			 		loginBean.setBackNews(backNews);
			 		loginBean.setSuccess(false);
				 }
		 	}
		 	RequestDispatcher dispatcher=request.getRequestDispatcher("showLoginMess.jsp");
		 	dispatcher.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	
	
	public void init(ServletConfig config) throws ServletException{ 
		super.init(config);
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){}
	}


}
