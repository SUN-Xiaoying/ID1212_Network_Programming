<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 2021-01-11
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
  <head class="java.util.Date">
    <title>Title</title>
  </head>
  <body>
    <jsp:useBean id="date" class="java.util.Date"/>
    <%
      date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
      //System.out.println(sdf.format(date));
    %>
  <p>${sdf.format(date)}</p>
  </body>
</html>
