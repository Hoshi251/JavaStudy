package raisetech.Student.Management.contoroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の検索が実行できること() throws Exception {
    Integer studentNo = 99;

    Student student = new Student();
    student.setStudentNo(studentNo);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentCourseList(List.of());

    when(service.searchStudent(studentNo)).thenReturn(detail);

    mockMvc.perform(get("/student/{studentNo}", studentNo))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(studentNo);
  }

  @Test
  void 受講生詳細の登録が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
        """
            {
                "student": {
                    "studentName" : "鴨葱鴨太郎",
                    "furigana" : "カモネギカモタロウ",
                    "nickname" : "カモ",
                    "email" : "test.example.com",
                    "city" : "千葉",
                    "age" : "25",
                    "gender" : "男性",
                    "remark" : ""
                },
                "studentCourseList" : [
                    {
                    "courseName" : "Javaコース"
                    }
                ]
            }
        
        
        """
    ))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
            {
                "student": {
                    "studentNo": 10,
                    "studentName": "鴨葱鴨太郎",
                    "furigana": "カモネギカモタロウ",
                    "nickname": "カモ",
                    "email": "test.example.com",
                    "city": "千葉",
                    "age": 25,
                    "gender": "男性",
                    "remark": "",
                    "deleted": false
                },
                "studentCourseList": [
                    {
                        "id": "7c783e13-dba2-4c05-86df-d1c6e012c8e4",
                        "studentNo": 10,
                        "courseName": "Javaコース",
                        "startDate": "2026-03-11T00:00:00",
                        "endDate": "2027-03-11T00:00:00"
                    }
                ]
            }
            
            
            """
        ))
        .andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());
  }

  @Test
  void 受講生詳細の受講生IDに数字以外を用いた時に入力チェックに掛かること() throws Exception {
    mockMvc.perform(get("/student/abc"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void 受講生詳細の受講生IDで適切な値を入力したときに入力チェックに異常が発生しないこと() throws Exception {
    Student student = new Student();
    student.setStudentNo(1);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentCourseList(List.of());

    when(service.searchStudent(1)).thenReturn(detail);

    mockMvc.perform(get("/student/1"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(1);
  }
}