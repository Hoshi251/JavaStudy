package raisetech.Student.Management.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentCourse;
import raisetech.Student.Management.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.studentListsearch()
        .stream()
        .filter(v -> v.getAge() >= 30 && v.getAge() <= 39)
        .toList();
  }

  public List<StudentCourse> searchStudentCourseList() {
    return repository.studentCourseListsearch()
        .stream()
        .filter(v -> "Java基礎コース".equals(v.getCourseName()))
        .toList();
  }
  
}
