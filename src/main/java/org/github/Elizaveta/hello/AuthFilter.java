package org.github.Elizaveta.hello;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter{
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String path = ((HttpServletRequest)servletRequest).getServletPath().toString();
        if(path.contains("register")||path.contains("authorization")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession httpSession = ((HttpServletRequest)servletRequest).getSession();

        String logged =  (String)httpSession.getAttribute("isLogged");

        if(logged!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            ((HttpServletResponse)servletResponse).sendRedirect("authorization");
        }
    }

    public void destroy() {

    }
}
