package schedule.service;

import schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleService {
    List<SysSchedule> findItemListByUid(int uid,String date);

    Integer addDefault(int uid,String date);

    Integer updateSchedule(SysSchedule sysSchedule);

    int removeSchedule(SysSchedule sysSchedule);
}
