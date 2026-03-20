package raisetech.Student.Management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter

public class Student {

  @Schema(description = "受講生ID",example = "1")
  private Integer studentId;

  @NotBlank
  @Schema(description = "受講生名",example = "山田太郎")
  private String studentName;

  @NotBlank
  @Schema(description = "フリガナ",example = "ヤマダタロウ")
  private String furigana;

  @NotBlank
  @Schema(description = "ニックネーム",example = "たろう")
  private String nickname;

  @NotBlank
  @Schema(description = "メールアドレス",example = "test@example.com")
  private String email;

  @NotBlank
  @Schema(description = "住所",example = "千葉")
  private String city;

  @NotBlank
  @Schema(description = "年齢",example = "21")
  private int age;

  @Schema(description = "性別",example = "男")
  private String gender;

  @Schema(description = "備考",example = "特記事項なし")
  private String remark;

  @Schema(description = "削除フラグ",example = "false")
  private boolean isDeleted;
}