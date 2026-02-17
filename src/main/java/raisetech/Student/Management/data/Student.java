package raisetech.Student.Management.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {
  private String id;
  private String studentName;
  private String furigana;
  private String nickname;
  private String email;
  private String city;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
}