package schedule.dao.impl;

import schedule.dao.BaseDao;
import schedule.dao.SysUserDao;
import schedule.pojo.SysUser;

import java.util.List;

public class SysUserDapImpl extends BaseDao implements SysUserDao {
    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        return(baseUpdate(sql,sysUser.getUsername(),sysUser.getUserPwd()));
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql = "select uid,username,user_pwd userPwd from sys_user where username = ?";
        List<SysUser> list = baseQuery(SysUser.class, sql, username);
        return list!=null&&list.size()>0?list.get(0):null;
    }
}
