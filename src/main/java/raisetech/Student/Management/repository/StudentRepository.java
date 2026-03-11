package raisetech.Student.Management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   *
   * @return 受講生一覧(全件)
   */
  List<Student> studentListSearch();

  /**
   * 受講生の検索を行います。
   *
   * @param studentNo 受講生ID
   * @return 受講生
   */
  Student studentSearch(Integer studentNo);


  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return 受講生のコース情報(全件)
   */
  List<StudentCourse> studentCourseListSearch();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studentNo 受講生ID
   * @return 受講生IDに紐づく受講生コース情報
   */
  List<StudentCourse> studentCourseSearch(Integer studentNo);

  /**
   * 受講生を新規登録します。IDに関しては自動採番を行う。
   *
   * @param student 受講生
   */
  void registerStudent(Student student);

  /**
   * 受講生コース情報を新規登録をします。
   *
   * @param course 受講生コース情報
   */
  void registerStudentCourse(StudentCourse course);

  /**
   * 受講生を更新します。
   *
   * @param student 受講生
   */
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース情報を更新します。
   *
   * @param course 受講生コース情報
   */
  void updateStudentCourse(StudentCourse course);
}