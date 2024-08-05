package schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysSchedule implements java.io.Serializable {
private Integer sid;
private Integer uid;
private String title;
private Integer completed;
private LocalDate sys_date;
}
