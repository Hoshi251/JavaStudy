package raisetech.Student.Management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.studentListsearch();
  }

  @Transactional
  public StudentDetail searchStudent(Integer studentNo) {
    Student student = repository.searchStudent(studentNo);
    List<StudentsCourses> studentsCourses = repository.studentsCoursesearch(student.getStudentNo());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourse(studentsCourses);
    return studentDetail;
  }

  public List<StudentsCourses> searchStudentCourseList() {
    return repository.studentCourseListsearch();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {

    // ① students 登録
    //リポジトリクラスのregisterStudentメソッドを使用、studentDetail.getStudent()を渡してね！
    //変数studetnIdを作成、その中にstudetnDeatil.getStudent().getidの中身を入れてね！
    //コース行そのものの主キー（id）を自分で作って入れてる。
    repository.registerStudent(studentDetail.getStudent());
    Integer studentNo = studentDetail.getStudent().getStudentNo();

    // ② courses 登録
    //StudentCoursesリストの中身を一つずつループ、studentDetai.getStudentCourseはStudentDetailクラスのgetStudentCouse自体を指している。
    for (StudentsCourses c : studentDetail.getStudentCourse()) {
      //空チェック、もし、getCourseNameが存在しない、または空文字なら次の処理はスキップしてね
      if (c.getCourseName() == null || c.getCourseName().isBlank()) {
        continue;
      }
      //上記でやったString studentId = studentDetail.getStudent().getId();をstudentscousesのstudetnidにセット
      //つまり、studentのNoとstudentcoureseのstudentNoを一致させる処理
      c.setStudentNo(studentNo);
      c.setId(UUID.randomUUID().toString());

      c.setStartDate(LocalDateTime.now());
      c.setEndDate(LocalDateTime.now().plusYears(1));

      repository.registerStudentCourse(c);
    }
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {

    // ① students 登録
    repository.updateStudent(studentDetail.getStudent());

    // ② courses 登録
    for (StudentsCourses c : studentDetail.getStudentCourse()) {
      repository.updateStudentCourse(c);
    }
  }
}
