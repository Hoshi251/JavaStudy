package raisetech.Student.Management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StudentCourse {
  private String id;
  private String studentid;
  private String courseName;
  private LocalDateTime startdate;
  private LocalDateTime enddate;
}
