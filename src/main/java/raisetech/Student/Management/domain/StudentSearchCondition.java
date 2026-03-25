package raisetech.Student.Management.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StudentSearchCondition {
  @Schema(description = "受講生ID",example = "1")
  private Integer studentId;

  @Schema(description = "受講生名",example = "山田太郎")
  private String studentName;

  @Schema(description = "フリガナ",example = "ヤマダタロウ")
  private String furigana;

  @Schema(description = "ニックネーム",example = "たろう")
  private String nickname;

  @Schema(description = "メールアドレス",example = "test@example.com")
  private String email;

  @Schema(description = "住所",example = "千葉")
  private String city;

  @Schema(description = "年齢",example = "21")
  private Integer age;

  @Schema(description = "性別",example = "男")
  private String gender;

  @Schema(description = "備考",example = "特記事項なし")
  private String remark;

  @Schema(description = "削除フラグ",example = "false")
  private Boolean isDeleted;
}
