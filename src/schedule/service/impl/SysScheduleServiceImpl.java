package schedule.service.impl;

import schedule.dao.SysScheduleDao;
import schedule.dao.impl.SysScheduleDaoImpl;
import schedule.pojo.SysSchedule;
import schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private SysScheduleDao sysScheduleDao = new SysScheduleDaoImpl();
    @Override
    public List<SysSchedule> findItemListByUid(int uid,String date) {
        return sysScheduleDao.findItemByUid(uid,date);
    }

    @Override
    public Integer addDefault(int uid,String date) {
        return sysScheduleDao.addDefualt(uid,date);
    }

    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        return sysScheduleDao.updateSchedule(sysSchedule);
    }

    @Override
    public int removeSchedule(SysSchedule sysSchedule) {
        return sysScheduleDao.removeSchedule(sysSchedule);
    }
}
