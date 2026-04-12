package raisetech.Student.Management.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import raisetech.Student.Management.data.Status;

@SpringBootTest
@Transactional
class StatusRepositoryTest {

  @Autowired
  private StatusRepository statusRepository;

  @Test
  void 申込状況を登録できる() {
    Status status = new Status();
    status.setCourseId(99);
    status.setStatus("仮申込");

    statusRepository.insertStatus(status);

    Status actual = statusRepository.selectStatusByCourseId(99);

    assertThat(actual).isNotNull();
    assertThat(actual.getCourseId()).isEqualTo(99);
    assertThat(actual.getStatus()).isEqualTo("仮申込");
  }

  @Test
  void コースIDで申込状況を取得できる() {
    Status actual = statusRepository.selectStatusByCourseId(7);

    assertThat(actual).isNotNull();
    assertThat(actual.getCourseId()).isEqualTo(7);
    assertThat(actual.getStatus()).isEqualTo("本申込");
  }

  @Test
  void 申込状況を更新できる() {
    Status status = new Status();
    status.setCourseId(8);
    status.setStatus("仮申込");

    statusRepository.updateStatus(status);

    Status actual = statusRepository.selectStatusByCourseId(8);

    assertThat(actual).isNotNull();
    assertThat(actual.getCourseId()).isEqualTo(8);
    assertThat(actual.getStatus()).isEqualTo("仮申込");
  }
}