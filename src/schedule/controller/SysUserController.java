package schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import schedule.common.Result;
import schedule.common.ResultCodeEnum;
import schedule.pojo.SysUser;
import schedule.service.SysUserService;
import schedule.service.impl.SysUserServiceImpl;
import schedule.util.MD5Util;
import schedule.util.WebUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/user/*")
public class SysUserController extends BaseController {
    private SysUserService userService =  new SysUserServiceImpl();


    /**
     * 接受用户登录请求，完成的登陆业务接口
     * @param request
     * @param response
     * @throws ServletException
     */
    protected void checkUsernameUsed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接受用户名
        // 调用服务层业务处理 查询改用户名是否有对应的用户
        //如果有 响应 已占用
        // 如果没有 响应 可用
        String username = request.getParameter("username");
        SysUser user = userService.findByUsername(username);
        Result result = Result.ok(null);
        if (user != null) {
            result = Result.build(null, ResultCodeEnum.USERNAME_EXIST);
        }
        //将字符串响应给客户端
        WebUtil.writeJson(response,result);


    }

    /**
     * 接受用户登陆请求，完成登陆的接口
      * @param request
     * @param response
     * @throws ServletException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SysUser user = WebUtil.readJson(request,SysUser.class);
        String username = user.getUsername();
        String userPwd = user.getUserPwd();
        Result result = null;
        //2 调用服务层方法，根据用户名查询用户信息
        SysUser loginUser = userService.findByUsername(username);
        if (loginUser == null) {
                result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        } else if (!MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd())) {
            result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        else{
            //登陆成功之后，将登陆的用户信息响应给客户端
            Map data = new HashMap();
            data.put("uid",loginUser.getUid());
            data.put("username",loginUser.getUsername());
            result = Result.ok(data);
        }
        //将结果响应给前端
        WebUtil.writeJson(response,result);
        // 3 判断密码是否匹配
        //4  跳转到首页

    }

    /**
     * 接受用户注册请求的业务处理方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 接受客户端提交的参数
        //1 接受用户名和方法,前端传回来的是JSON串 则转换为User对象获取信息
        SysUser user = WebUtil.readJson(request,SysUser.class);
//        String username = request.getParameter("username");
//        String userPwd = request.getParameter("userPwd");
//        // 2 调用服务层方法 完成注册功能
//        //将参数放入一个 SysUser对象中，再调用regist方法是传入
//        SysUser user = new SysUser(null,username,userPwd);
        int rows = userService.regist(user);


        // 3 根据注册结果 做出相应页面的跳转
        Result result = Result.ok(null);
        if(rows < 1){
            result = Result.build(null,ResultCodeEnum.USERNAME_EXIST);
        }
        WebUtil.writeJson(response,result);
    }
}
