package raisetech.Student.Management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.Student.Management.contoroller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentCourse;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {

  private final StudentRepository repository;
  private final StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return  受講生詳細一覧(全件)
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.studentListSearch();
    List<StudentCourse> studentCourseList = repository.studentCourseListSearch();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生詳細検索です。IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param studentId 受講生ID
   * @return 受講生詳細
   */
  @Transactional
  public StudentDetail searchStudent(Integer studentId) {
    Student student = repository.studentSearch(studentId);
    List<StudentCourse> studentsCourse = repository.studentCourseSearch(student.getStudentId());
    return new StudentDetail(student, studentsCourse);
  }

  /**
   * 受講生詳細の登録を行います。
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します。
   *
   * @param studentDetail 受講生詳細
   * @return 登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {

    // ① students 登録
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);
    Integer studentId = student.getStudentId();

    // ② courses 登録
    for (StudentCourse studentCourse : studentDetail.getStudentCourseList()) {
      if (studentCourse.getCourseName() == null || studentCourse.getCourseName().isBlank()) {
        continue;
      }
      initStudentsCourses(studentCourse, studentId);
      repository.registerStudentCourse(studentCourse);
    }
    return studentDetail;
  }

  /**
   *受講生コース登録時の初期設定処理
   *
   * @param studentCourse 受講生コース情報
   * @param studentId 受講生ID
   */
  public void initStudentsCourses(StudentCourse studentCourse, Integer studentId) {
    studentCourse.setStudentId(studentId);
    LocalDateTime now = LocalDateTime.now();

    studentCourse.setStartDate(now);
    studentCourse.setEndDate(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います。受講生の情報と受講生コース情報をそれぞれ更新します。
   *
   * @param studentDetail 受講生詳細
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    // ① students 登録
    repository.updateStudent(studentDetail.getStudent());
    // ② courses 登録
    for (StudentCourse c : studentDetail.getStudentCourseList()) {
      repository.updateStudentCourse(c);
    }
  }
}
