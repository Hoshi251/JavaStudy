package raisetech.Student.Management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StudentCourse {

  private String id;
  private Integer studentNo;
  private String courseName;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
