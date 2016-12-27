<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.mybean.data.*"%>
<jsp:useBean id="upFile" type="com.mybean.data.UploadFile" scope="request" />
<HTML>
<HEAD><%@ include file="main.html"%></HEAD>
<HTML>
<BODY bgcolor=yellow>
	<CENTER>
		<Font size=2 color=blue> <BR> <jsp:getProperty
				name="upFile" property="backNews" /> </Font> <BR>
		<font size=2>上传的文件名字：<jsp:getProperty name="upFile"
				property="fileName" /> 保存后的文件名字：<jsp:getProperty name="upFile"
				property="savedFileName" /> <BR> <img src=img/<jsp:getProperty name="upFile" property="savedFileName" /> width=500
			height=500>图像效果 </img> </FONT>
	</CENTER>
</BODY>
</HTML>