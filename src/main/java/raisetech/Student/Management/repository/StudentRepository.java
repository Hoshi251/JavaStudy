package raisetech.Student.Management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> studentListsearch();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> studentCourseListsearch();

  @Insert("""
    INSERT INTO students
      (student_name, furigana, nickname, email, city, age, gender, remark, is_deleted)
    VALUES
      (#{studentName}, #{furigana}, #{nickname}, #{email}, #{city}, #{age}, #{gender}, #{remark}, #{isDeleted})
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
}