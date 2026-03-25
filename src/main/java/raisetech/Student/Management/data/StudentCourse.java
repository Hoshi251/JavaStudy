package raisetech.Student.Management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Setter
@Getter

public class StudentCourse {

  @Schema(description = "コースID",example = "1")
  private Integer courseId;

  @Schema(description = "受講生ID",example = "1")
  private Integer studentId;

  @Schema(description = "受講コース名",example = "Javaコース")
  private String courseName;

  @Schema(description = "受講開始日",example = "2026-03-12T10:00:00")
  private LocalDateTime startDate;

  @Schema(description = "受講終了日",example = "2027-03-12T10:00:00")
  private LocalDateTime endDate;

  private String displayStatus; // 画面表示用
}
