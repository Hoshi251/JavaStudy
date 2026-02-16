package raisetech.Student.Management;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> studentListsearch();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> studentCourseListsearch();
}