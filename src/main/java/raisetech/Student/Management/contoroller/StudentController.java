package raisetech.Student.Management.contoroller;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.Student.Management.contoroller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

@RestController
public class StudentController {

  private final StudentService service;
  private final StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentCourseList();
    return converter.convertStudentDetails(students,studentsCourses);
  }

  //htmlで使える箱を作ってhtml画面を表示させる処理
  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(new Student());

    List<StudentsCourses> list = new ArrayList<>();
    list.add(new StudentsCourses());
    list.add(new StudentsCourses());
    studentDetail.setStudentCourse(list);

    //html側の箱:studentDetail、java側の箱:studentDetail
    //studetnDetail == dtudetnDetail、同じ名前でもいいんかい
    //インスタンス生成をしているのでstudentDetailでもいける
    model.addAttribute("studentDetail", studentDetail);
    //registerSudent.htmlを表示させる、飛ぶ
    return "registerStudent";
  }

  //データを「変更する処理」
  @PostMapping("/registerStudent")
  //htmlで取得した情報をjavaに持ってきて、エラーかどうかを確認する処理
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {

    if (result.hasErrors()) {
      return "registerStudent";
    }
    //serviceクラスのregisterstudentメソッドを実行する、引数にstudentDetailを渡してね！
    service.registerStudent(studentDetail);
    // ①新規受講生情報を登録する処理を実装する。
    // ②コース情報も一緒に登録できるように実装する。コースは単体で良い。
    return "redirect:/studentList";
  }

    @PostMapping("/updateStudent")
    public ResponseEntity<String> updateStudent (@RequestBody StudentDetail studentDetail){
      service.updateStudent(studentDetail);
      return ResponseEntity.ok("更新処理が成功しました");
    }
  }