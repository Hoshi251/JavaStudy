package raisetech.Student.Management.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.Student.Management.data.Status;
import raisetech.Student.Management.repository.StatusRepository;

@Service
public class StatusService {

  private final StatusRepository statusRepository;

  @Autowired
  public StatusService(StatusRepository statusRepository) {
    this.statusRepository = statusRepository;
  }


  public Status getStatus(Integer courseId) {
    return statusRepository.selectStatusByCourseId(courseId);
  }

  public void registerStatus(Status status) {
    statusRepository.insertStatus(status);
  }

  public void updateStatus(Status status) {
    statusRepository.updateStatus(status);
  }

  public String decideDisplayStatus(String baseStatus, LocalDate startDate, LocalDate endDate) {

    if (baseStatus == null) {
      return null;
    }

    if (startDate == null || endDate == null) {
      return baseStatus;
    }

    LocalDate today = LocalDate.now();

    if ("仮申込".equals(baseStatus)) {
      return "仮申込";
    }

    if ("本申込".equals(baseStatus)) {
      if (today.isBefore(startDate)) {
        return "本申込";
      }
      if (!today.isAfter(endDate)) {
        return "受講中";
      }
      return "受講終了";
    }

    return baseStatus;
  }

}
