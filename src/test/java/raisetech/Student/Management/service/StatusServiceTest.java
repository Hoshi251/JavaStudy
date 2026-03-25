package raisetech.Student.Management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import raisetech.Student.Management.repository.StatusRepository;

class StatusServiceTest {

  private final StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
  private final StatusService statusService = new StatusService(statusRepository);

  @Test
  void 仮申込の場合は仮申込を返す() {
    String actual = statusService.decideDisplayStatus(
        "仮申込",
        LocalDate.of(2026, 3, 1),
        LocalDate.of(2026, 3, 31)
    );

    assertEquals("仮申込", actual);
  }

  @Test
  void 本申込かつ開始日前の場合は本申込を返す() {
    LocalDate today = LocalDate.now();

    String actual = statusService.decideDisplayStatus(
        "本申込",
        today.plusDays(1),
        today.plusDays(30)
    );

    assertEquals("本申込", actual);
  }

  @Test
  void 本申込かつ期間中の場合は受講中を返す() {
    LocalDate today = LocalDate.now();

    String actual = statusService.decideDisplayStatus(
        "本申込",
        today.minusDays(1),
        today.plusDays(1)
    );

    assertEquals("受講中", actual);
  }

  @Test
  void 本申込かつ終了後の場合は受講終了を返す() {
    LocalDate today = LocalDate.now();

    String actual = statusService.decideDisplayStatus(
        "本申込",
        today.minusDays(30),
        today.minusDays(1)
    );

    assertEquals("受講終了", actual);
  }

  @Test
  void baseStatusがnullの場合はnullを返す() {
    String actual = statusService.decideDisplayStatus(
        null,
        LocalDate.of(2026, 3, 1),
        LocalDate.of(2026, 3, 31)
    );

    assertNull(actual);
  }

  @Test
  void 本申込かつ開始日終了日がnullの場合は本申込を返す() {
    String actual = statusService.decideDisplayStatus(
        "本申込",
        null,
        null
    );

    assertEquals("本申込", actual);
  }
}