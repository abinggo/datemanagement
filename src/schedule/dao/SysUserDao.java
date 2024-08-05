package schedule.dao;


import schedule.pojo.SysUser;

public interface SysUserDao {
    /**
     * 向数据库中添加一条增加用户记录的方法
     * @param sysUser 要增加的记录的username和user_pwd字段以SysUser实体类对象的形式接受
     * @return 增加成功返回1 增加失败返回0
     */
    int addSysUser(SysUser sysUser);

    /**
     * 根据用户名获取完整用户信息的方法
     * @param username
     * @return 如果找到了则返回对应SysUser对象，否则则返回null
     */
    SysUser findByUsername(String username);
}
