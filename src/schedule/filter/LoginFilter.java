package schedule.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import schedule.pojo.SysUser;

import java.io.IOException;
//@WebFilter(urlPatterns = {"/showSchedule.html","/schedule/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //做登陆校验，获得session域对象 从session域中获得登陆的用户对象
        //判断用户对象是否为空 没登陆 到login.html 登陆过放行
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        SysUser user = (SysUser)session.getAttribute("Sysuser");
        if(user == null) {
            response.sendRedirect("/login.html");
        }else {
            filterChain.doFilter(request, response);
        }
    }
}
