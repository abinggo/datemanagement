package schedule.service;

import schedule.pojo.SysUser;

/*
* 该接口定义了以Sys_user表格为核心的业务功能取
* */
public interface SysUserService {
    /**
     * 注册用户的方法
     * @param  sysUser 要注册的用户名和密码以SysUser对象的形式接受
     * @return 注册返回成功1 否则0
     */

    int regist(SysUser sysUser);

    /**
     * 根据用户名获取完整用户信息的方法
     * @param username
     * @return 如果找到了则返回对应SysUser对象，否则则返回null
     */
    SysUser findByUsername(String username);
}
