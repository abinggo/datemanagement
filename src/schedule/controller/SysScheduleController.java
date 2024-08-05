package schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import schedule.common.Result;
import schedule.common.ResultCodeEnum;
import schedule.pojo.SysSchedule;
import schedule.service.SysScheduleService;
import schedule.service.impl.SysScheduleServiceImpl;
import schedule.util.WebUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 增 /schedule/add
* 删 /schedule/remove
* 改 /schedule/update
* 查 /schedule/find
*
*
*
* */

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    private SysScheduleService sysScheduleService = new SysScheduleServiceImpl();


    protected void removeSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        //接受JSON串转为SysSchedule
        SysSchedule sysSchedule = WebUtil.readJson(request, SysSchedule.class);
        int rows = sysScheduleService.removeSchedule(sysSchedule);
        Result result = new Result();
        if (rows>0) {
            result = Result.ok(null);

        }
        else{
            result = Result.build(null, ResultCodeEnum.DELETEERROR);
        }
        WebUtil.writeJson(response,result);
    }

    protected void updateSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        //接受JSON串转为SysSchedule
        SysSchedule sysSchedule = WebUtil.readJson(request, SysSchedule.class);
        //调用服务层方法
        int rows = sysScheduleService.updateSchedule(sysSchedule);
        Result result = new Result();
        if (rows>0) {
            result = Result.ok(null);

        }
        else{
            result = Result.build(null, ResultCodeEnum.SAVEERROR);
        }
        WebUtil.writeJson(response,result);
    }

    protected void addDefaultShedule(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        // 接受请求中的uid参数
        int uid  = Integer.parseInt(request.getParameter("uid"));
        String date = request.getParameter("date");

        //调用服务层结果
        int rows =sysScheduleService.addDefault(uid,date);
        Result result = new Result();
        if (rows>0) {
            result = Result.ok(null);

        }
        else{
            result = Result.build(null, ResultCodeEnum.ADDERROR);
        }
        WebUtil.writeJson(response,result);
        //获得结果并返回响应
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        System.out.println("update");
    }
    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        System.out.println("remove");
    }
    protected void findAllSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        //接受请求uid参数
        int uid = Integer.parseInt(request.getParameter("uid"));
        //接受请求日期参数
        String date = request.getParameter("date");
        //查询用户的所有日程放入一个Result对象
        List<SysSchedule> itemlist = sysScheduleService.findItemListByUid(uid,date);
        //将Result对象转为json串响应给客户端
        Map data = new HashMap();
        data.put("itemlist", itemlist);
        Result result = Result.ok(data);
        WebUtil.writeJson(response,result);
    }

}
