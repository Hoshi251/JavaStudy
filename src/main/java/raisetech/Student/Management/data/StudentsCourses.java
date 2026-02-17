package raisetech.Student.Management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StudentsCourses {
  private String id;
  private String studentid;
  private String courseName;
  private LocalDateTime startdate;
  private LocalDateTime enddate;
}
