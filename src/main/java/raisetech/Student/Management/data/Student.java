package raisetech.Student.Management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter

public class Student {


  private Integer studentId;

  private String studentName;

  private String furigana;

  private String nickname;

  private String email;

  private String city;

  private int age;

  private String gender;

  private String remark;

  private boolean isDeleted;

  private String iwamoto;
}