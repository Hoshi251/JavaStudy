package raisetech.Student.Management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> studentListsearch();

  @Select("SELECT * FROM students WHERE student_no = #{studentNo}")
  Student searchStudent(Integer studentNo);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> studentCourseListsearch();

  @Select("SELECT * FROM students_courses WHERE student_no = #{studentNo}")
  List<StudentsCourses> studentsCoursesearch(Integer studentNo);

  @Insert("""
    INSERT INTO students
      (student_name, furigana, nickname, email, city, age, gender, remark, is_deleted)
    VALUES
      (#{studentName}, #{furigana}, #{nickname}, #{email}, #{city}, #{age}, #{gender}, #{remark}, false)
""")
  @Options(useGeneratedKeys = true, keyProperty = "studentNo")
  void registerStudent(Student student);

  @Insert("""
    INSERT INTO students_courses
      (id, student_no, course_name, start_date, end_date)
    VALUES
      (#{id}, #{studentNo}, #{courseName}, #{startDate}, #{endDate})
""")
  void registerStudentCourse(StudentsCourses course);

  @Update("""
    UPDATE students SET student_name = #{studentName},furigana = #{furigana},nickname = #{nickname},email =#{email},city = #{city},age = #{age},gender = #{gender},remark = #{remark},is_deleted = #{isDeleted} WHERE student_no = #{studentNo}
    """)
  void updateStudent(Student student);

  @Update("""
    UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}""")
  void updateStudentCourse(StudentsCourses course);
}