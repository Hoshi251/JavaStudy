package raisetech.Student.Management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentCourse;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> studentListsearch();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> studentCourseListsearch();
}