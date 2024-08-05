package schedule.dao;

import schedule.pojo.SysSchedule;

import java.util.List;
/*
* 介绍一下当前类
* 作者
* 时间
* */

/*
* dao data access object 用于对数据库的增删改查*/
public interface SysScheduleDao {
    /*该方法用于向数据中增加一条日程记录
    * 返回影响数据库记录的函数，为0说明增加失败，行数大于0说明增加成功*/

  /*
  * 查询用户的所有日程，将所有日程放入一个List
  * */

    List<SysSchedule> findItemByUid(int uid,String date);

    Integer addDefualt(int uid,String date);

    Integer updateSchedule(SysSchedule sysSchedule);

   Integer removeSchedule(SysSchedule sysSchedule);
}
