package raisetech.Student.Management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Schema
@Getter
@Setter

public class Status {

  private Integer statusId;
  private Integer courseId;
  private String status;

}
