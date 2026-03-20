package raisetech.Student.Management.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.Student.Management.contoroller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentCourse;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void  before() {
    sut = new StudentService(repository,converter);
  }

  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.studentListSearch()).thenReturn(studentList);
    when(repository.studentCourseListSearch()).thenReturn(studentCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).studentListSearch();
    verify(repository, times(1)).studentCourseListSearch();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生詳細検索_リポジトリの処理が適切に呼び出せていること() {
    // 準備
    Integer studentId = 999;
    Student student = new Student();
    student.setStudentId(studentId);

    List<StudentCourse> studentCourses = new ArrayList<>();
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId(studentId);
    studentCourses.add(studentCourse);

    when(repository.studentSearch(studentId)).thenReturn(student);
    when(repository.studentCourseSearch(studentId)).thenReturn(studentCourses);

    // 実行
    StudentDetail actual = sut.searchStudent(studentId);

    verify(repository, times(1)).studentSearch(studentId);
    verify(repository, times(1)).studentCourseSearch(studentId);
  }

  @Test
  void 受講生詳細の登録_リポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseName("Javaコース");

    List<StudentCourse> studentCourseList = List.of(studentCourse);
    StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

    sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生詳細の登録_初期化処理が行われること() {
    Integer studentNo = 999;
    Student student = new Student();
    student.setStudentId(studentNo);
    StudentCourse studentCourse = new StudentCourse();

    sut.initStudentsCourses(studentCourse,student.getStudentId());

    Assertions.assertEquals(studentNo,studentCourse.getStudentId());
    Assertions.assertEquals(LocalDateTime.now().getHour(), studentCourse.getStartDate().getHour());
    Assertions.assertEquals(LocalDateTime.now().plusYears(1).getYear(), studentCourse.getEndDate().getYear());
  }

  @Test
  void 受講生詳細の更新_リポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    StudentDetail studentDetail = new StudentDetail(student,studentCourseList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentCourse(studentCourse);
  }
}