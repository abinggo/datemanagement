package schedule.dao.impl;

import schedule.dao.BaseDao;
import schedule.dao.SysScheduleDao;
import schedule.pojo.SysSchedule;
import schedule.pojo.SysUser;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {


    @Override
    public List<SysSchedule> findItemByUid(int uid,String date) {
        String sql = "select * from sys_schedule where uid = ? and sys_date = ?";
        List<SysSchedule> itemlist = baseQuery(SysSchedule.class,sql,uid,date);
        return itemlist;
    }

    @Override
    public Integer addDefualt(int uid,String date) {
        String sql = "INSERT INTO sys_schedule (sid, uid, title, completed, sys_date) VALUES (DEFAULT, ?, '请输入日程', 0, ?)";
        int rows = baseUpdate(sql,uid,date);
        return rows;
    }

    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        String sql = "update sys_schedule set title = ?,completed = ?,sys_date=? where sid = ?";
        int rows = baseUpdate(sql,sysSchedule.getTitle(),sysSchedule.getCompleted(),sysSchedule.getSys_date(),sysSchedule.getSid());
        return rows;
    }

    @Override
    public Integer removeSchedule(SysSchedule sysSchedule) {
        String sql = "delete from sys_schedule where sid =?";
        int rows = baseUpdate(sql,sysSchedule.getSid());
        return rows;
    }


}
