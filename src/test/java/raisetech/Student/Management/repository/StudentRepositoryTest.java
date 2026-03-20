package raisetech.Student.Management.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.Student.Management.data.Student;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.studentListSearch();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setStudentId(10);
    student.setStudentName("田中英和");
    student.setFurigana("タナカヒデカズ");
    student.setNickname("タナカ");
    student.setEmail("test@example.com");
    student.setCity("愛媛");
    student.setAge(38);
    student.setGender("男性");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.studentListSearch();

    assertThat(actual).hasSize(6);
  }
}