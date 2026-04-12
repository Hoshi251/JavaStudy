package raisetech.Student.Management.contoroller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
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
  void 受講生詳細の一覧検索が実行できること() throws Exception {
    when(service.searchStudentList(any())).thenReturn(List.of());

    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList(any());
  }

  @Test
  void 条件を指定した受講生一覧検索が実行できること() throws Exception {
    when(service.searchStudentList(any())).thenReturn(List.of());

    mockMvc.perform(get("/studentList").param("city", "渋谷区"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList(any());
  }
  @Test
  void 受講生詳細の検索が実行できること() throws Exception {
    Integer studentNo = 99;

    Student student = new Student();
    student.setStudentId(studentNo);

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
                    "studentId": 10,
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
                      "courseId": 7,
                      "studentId": 10,
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
    student.setStudentId(1);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentCourseList(List.of());

    when(service.searchStudent(1)).thenReturn(detail);

    mockMvc.perform(get("/student/1"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(1);
  }

}