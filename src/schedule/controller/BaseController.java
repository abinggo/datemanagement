package schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;

public class BaseController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        String methodname = split[split.length - 1];
        //使用反射 通过方法名来获取方法
        Class aClass = this.getClass();
        //获取方法
        try {
            Method declareMehod = aClass.getDeclaredMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
            //防止万一 进行暴力破解方法
            declareMehod.setAccessible(true);
            //执行方法
            declareMehod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
