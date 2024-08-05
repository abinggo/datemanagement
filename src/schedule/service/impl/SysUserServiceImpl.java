package schedule.service.impl;

import schedule.dao.SysUserDao;
import schedule.dao.impl.SysUserDapImpl;
import schedule.pojo.SysUser;
import schedule.service.SysUserService;
import schedule.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao userDao = new SysUserDapImpl();
    @Override
    public int regist(SysUser sysUser) {
        //将用户的明文密码转为秘文密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        //调用dao层的方法 将信息存入
        int rows = userDao.addSysUser(sysUser);
        //将sysUser的信息存入数据库
        return rows;
    }

    @Override
    public SysUser findByUsername(String username) {
        SysUser user = userDao.findByUsername(username);
        return user;
    }
}
