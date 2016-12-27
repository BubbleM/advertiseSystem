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
import com.mybean.data.MemberInform;
import com.mybean.data.ShowByPage;
import com.sun.rowset.CachedRowSetImpl;

public class HandleDatabase extends HttpServlet {
	CachedRowSetImpl rowSet=null;
	/**
	 * Constructor of the object.
	 */
	public HandleDatabase() {
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

		HttpSession session=request.getSession(true);
		 Login login=(Login)session.getAttribute("login"); //获取用户登录时的Javabean
		 boolean ok=true;
		 if(login==null)
		 { ok=false;
		 response.sendRedirect("login.jsp"); //重定向到登录页面
		 }
		 if(ok==true)
		 { continueDoGet(request,response);
		 }

	}
	
	 public void continueDoGet(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException,IOException
			 { MemberInform inform=new MemberInform();
			 request.setAttribute("inform",inform);
			 String logname=request.getParameter("logname");
			 Connection con=null; 
		
			 String
			uri="jdbc:mysql://localhost:3306/e-commerce?useUnicode=true&characterEncoding=utf-8";
			 try{ con=DriverManager.getConnection(uri,"root","123456");
			 Statement sql=con.createStatement();
			 ResultSet rs=
			sql.executeQuery("SELECT * FROM member where logname = '"+logname+"'");
			 if(rs.next())
			 { inform.setLogname(rs.getString(1));
			 inform.setAdvertiseTitle(rs.getString(3));
			 inform.setPhone(rs.getString(4));
			 inform.setEmail(rs.getString(5));
			 inform.setMessage(rs.getString(6));
			 inform.setPic(rs.getString(7));
			 inform.setBackNews("查询到的会员信息：");
			 }
			 con.close();
			 RequestDispatcher dispatcher=
			request.getRequestDispatcher("showLookedMember.jsp");//转发
			 dispatcher.forward(request, response);
			 }
			 catch(SQLException exp)
			 { inform.setBackNews(""+exp);System.out.println("ok1"+exp);
			 }
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
		 Login login=(Login)session.getAttribute("login"); //获取用户登录时的Javabean
		 boolean ok=true;
		 if(login==null){ 
			 ok=false;
			 response.sendRedirect("login.jsp"); //重定向到登录页面
		 }
		 if(ok==true){ 
			 continueDoPost(request,response);
		 }
	}
	
	
	public void continueDoPost(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException,IOException
			 { HttpSession session=request.getSession(true);
			 Connection con=null;
			 StringBuffer presentPageResult=new StringBuffer();
			 ShowByPage showBean=null;
			 try{ showBean=(ShowByPage)session.getAttribute("show");
			 if(showBean==null)
			 { showBean=new ShowByPage(); //创建Javabean对象
			 session.setAttribute("show",showBean);
			 }
			 }
			 catch(Exception exp)
			 { showBean=new ShowByPage();
			 session.setAttribute("show",showBean);
			 }
			 showBean.setPageSize(3); //每页显示3条记录
			 int showPage=Integer.parseInt(request.getParameter("showPage"));
			 if(showPage>showBean.getPageAllCount())
			 showPage=1;
			 if(showPage<=0)
			 showPage=showBean.getPageAllCount();
			 showBean.setShowPage(showPage);
			 int pageSize=showBean.getPageSize();
			 String
			uri="jdbc:mysql://localhost:3306/advertisement?useUnicode=true&characterEncoding=utf-8";
			 try{ con=DriverManager.getConnection(uri,"root","123456");
			 Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			 ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs=sql.executeQuery("SELECT * FROM member");
			 rowSet=new CachedRowSetImpl(); //创建行集对象
			 rowSet.populate(rs);
			 con.close(); //关闭连接
			 showBean.setRowSet(rowSet); //数据存储在showBean中
			 rowSet.last();
			 int m=rowSet.getRow(); //总行数
			 int n=pageSize;
			 int pageAllCount=((m%n)==0)?(m/n):(m/n+1);
			 showBean.setPageAllCount(pageAllCount);//数据存储在showBean中
			 presentPageResult=show(showPage,pageSize,rowSet);
			 showBean.setPresentPageResult(presentPageResult); 
		
			 }
			 catch(SQLException exp){}
			 RequestDispatcher dispatcher=
			request.getRequestDispatcher("showAllMember.jsp");//转发
			 dispatcher.forward(request, response);
			 }
			 public StringBuffer show(int page,int pageSize,CachedRowSetImpl rowSet)
			 { StringBuffer str=new StringBuffer();
			 try{ rowSet.absolute((page-1)*pageSize+1);
			 for(int i=1;i<=pageSize;i++)
			 { str.append("<tr>");
			 str.append("<td>"+rowSet.getString(1)+"</td>");
			 str.append("<td>"+rowSet.getString(3)+"</td>");
			 str.append("<td>"+rowSet.getString(4)+"</td>");
			 str.append("<td>"+rowSet.getString(5)+"</td>");
			 str.append("<td>"+rowSet.getString(6)+"</td>");
			 String s="<img src=image/"+rowSet.getString(7)+" width=100 height=100/>";
			 str.append("<td>"+s+"</td>");
			 str.append("</tr>");
			 rowSet.next();
			 }
			 }
			 catch(SQLException exp){}
			 return str;
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
