package raisetech.Student.Management.contoroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.Student.Management.data.Status;
import raisetech.Student.Management.service.StatusService;

  @RestController
  public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
      this.statusService = statusService;
    }

    @GetMapping("/status/{courseId}")
    public Status getStatus(@PathVariable Integer courseId) {
      return statusService.getStatus(courseId);
    }

    @PostMapping("/status")
    public void registerStatus(@RequestBody Status status) {
      statusService.registerStatus(status);
    }

    @PutMapping("/status/{courseId}")
    public void updateStatus(@PathVariable Integer courseId, @RequestBody Status status) {
      status.setCourseId(courseId);
      statusService.updateStatus(status);
    }
}
