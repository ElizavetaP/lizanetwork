package org.github.Elizaveta.hello;

HTTP Status 500 - An exception occurred processing JSP page /main.jsp at line 39

type Exception report

message An exception occurred processing JSP page /main.jsp at line 39

description The server encountered an internal error that prevented it from fulfilling this request.

exception

org.apache.jasper.JasperException: An exception occurred processing JSP page /main.jsp at line 39

36:                 <td>
37:                 <div id="logo">
38:                 <a href="otheruser?id=${person.getID()}">
39:                 <img src="/static/${photos.getPhoto_name()}" width="50"></a>
40:                 </div>
41:                 </td>
42:                 <td>${person.getFirstName()}</td>


Stacktrace:
		org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:579)
	org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:481)
	org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:385)
	org.apache.jasper.servlet.JspServlet.service(JspServlet.java:329)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	org.github.Elizaveta.hello.Hello.doGet(Hello.java:36)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:622)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	org.github.Elizaveta.hello.AuthFilter.doFilter(AuthFilter.java:29)

root cause

javax.el.MethodNotFoundException: Method not found: class java.util.ArrayList.getPhoto_name()
	javax.el.Util.findWrapper(Util.java:351)
	javax.el.Util.findMethod(Util.java:213)
	javax.el.BeanELResolver.invoke(BeanELResolver.java:156)
	org.apache.jasper.el.JasperELResolver.invoke(JasperELResolver.java:147)
	org.apache.el.parser.AstValue.getValue(AstValue.java:159)
	org.apache.el.ValueExpressionImpl.getValue(ValueExpressionImpl.java:184)
	org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate(PageContextImpl.java:944)
	org.apache.jsp.main_jsp._jspx_meth_c_005fforEach_005f0(main_jsp.java:216)
	org.apache.jsp.main_jsp._jspService(main_jsp.java:166)
	org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:443)
	org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:385)
	org.apache.jasper.servlet.JspServlet.service(JspServlet.java:329)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	org.github.Elizaveta.hello.Hello.doGet(Hello.java:36)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:622)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	org.github.Elizaveta.hello.AuthFilter.doFilter(AuthFilter.java:29)

note The full stack trace of the root cause is available in the Apache Tomcat/8.5.3 logs.
Apache Tomcat/8.5.3