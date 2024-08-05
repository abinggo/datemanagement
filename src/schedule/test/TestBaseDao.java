package schedule.test;

import org.junit.BeforeClass;
import org.junit.Test;
import schedule.dao.BaseDao;
import schedule.pojo.SysUser;

import java.util.List;

public class TestBaseDao {

    private static BaseDao dao;
    @BeforeClass
    public static void initBaseDao(){
        dao = new BaseDao();
    }
   @Test
    public void testBaseQueryObeject(){
        //查询用户数量  BaseQueryObeject查询的结果集是单行，单列的一个值的方法
        String sql = "select count(*) from sys_user";
        Long count = dao.baseQueryObject(Long.class,sql);
       System.out.println(count);
    }
    @Test
    public void testBaseQuery(){
        //要注意字段名字要一致
        String sql = "select uid,username,user_pwd userPwd from sys_user";
        List<SysUser> users = dao.baseQuery(SysUser.class,sql);
        users.forEach(System.out::println);
    }
    @Test
    public void testBaseQueryObject(){
        String sql = "insert into sys_schedule values(DEFAULT,?,?,?)";
        int rows = dao.baseUpdate(sql,1,"学习JAVA",0);
        System.out.println(rows);
    }
}
