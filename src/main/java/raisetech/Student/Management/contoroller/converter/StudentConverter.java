package raisetech.Student.Management.contoroller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentsCourses> studentsCourses) {

    List<StudentDetail> studentDetails = new ArrayList<>();

    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
          .filter(sc -> student.getStudentNo() != null
              && sc.getStudentNo() != null
              && student.getStudentNo().equals(sc.getStudentNo()))
          .collect(Collectors.toList());

      studentDetail.setStudentCourse(convertStudentCourses);
      studentDetails.add(studentDetail);
    });

    return studentDetails;
  }
}
