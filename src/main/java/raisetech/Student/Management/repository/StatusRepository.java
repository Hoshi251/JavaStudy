package raisetech.Student.Management.repository;

import org.apache.ibatis.annotations.Mapper;
import raisetech.Student.Management.data.Status;

@Mapper
public interface StatusRepository {

  Status selectStatusByCourseId(Integer courseId);

  // 申込状況登録
  void insertStatus(Status status);

  void updateStatus(Status status);
}