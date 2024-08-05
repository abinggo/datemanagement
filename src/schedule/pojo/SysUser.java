package schedule.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/*
* 1 实体类的类名和表格类对应
* 2 实体类的属性名和列名对应
* 3 每个属性都必须是私有的
* 4 每个属性都具备getter setter
* 5 必须具备无参构造器
* 6 应该实现序列化接口
* 7 应该重写类的hashcode和equals 方法
* 8 toString是否重写都可以
*
*
*使用lombok帮助我们实现这些内容 getter setter euqals hashcode 有参 无参
*lombok使用步骤
*   1 检查是否已经安装了插件
*   2 是否够选enable annotation procession
*   3 导入依赖
*
* */
@AllArgsConstructor //添加了全参构造
@NoArgsConstructor
@Data //getter setter equals hashcode
public class SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;

}
