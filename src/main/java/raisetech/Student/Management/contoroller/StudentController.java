package raisetech.Student.Management.contoroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APOとして実行されるControllerです。
 */
@Tag(name = "Student", description = "受講生管理API")
@Validated
@RestController
public class StudentController {

  public final StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細の一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細一覧(全件)
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @ApiResponse(responseCode = "200", description = "取得成功")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細の検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   *
   * @param studentNo 受講生ID
   * @return 受講生
   */
  @Operation(summary = "受講生検索", description = "受講生IDを指定して受講生情報を取得します。")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "取得成功"),
      @ApiResponse(responseCode = "400", description = "リクエスト不正"),
      @ApiResponse(responseCode = "600", description = "該当データなし")})
  @GetMapping("/student/{studentNo}")
  public StudentDetail getStudent(@PathVariable @Min(1) @Max(100) Integer studentNo) {
    return service.searchStudent(studentNo);
  }

  /**
   * 受講生詳細の登録を行います。
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生登録", description = "受講生を登録します。")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "登録成功"),
      @ApiResponse(responseCode = "400", description = "入力エラー")})
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います(論理削除)
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生更新", description = "受講生情報を更新します。")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "更新成功"),
      @ApiResponse(responseCode = "400", description = "リクエスト不正"),
      @ApiResponse(responseCode = "404", description = "受講生が存在しない")})
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent (@RequestBody StudentDetail studentDetail){
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました");
  }
}