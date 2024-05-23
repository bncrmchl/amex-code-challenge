package com.bncrmchl.amex;

import com.bncrmchl.amex.controller.StudentController;
import com.bncrmchl.amex.model.Student;
import com.bncrmchl.amex.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class AmexCodeChallengeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllStudents() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setClassId("10A");

        given(studentService.findAllStudents()).willReturn(Arrays.asList(student));

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void getStudentById() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");

        given(studentService.findStudentById(1L)).willReturn(Optional.of(student));

        mockMvc.perform(get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void searchStudentsByClassId() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setClassId("10A");

        given(studentService.searchStudentsByClassId("10A")).willReturn(Arrays.asList(student));

        mockMvc.perform(get("/students/search/class?class=10A")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].class").value("10A"));
    }

    @Test
    void searchStudentsByName() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setClassId("10A");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Doe");
        student2.setClassId("10A");

        Student student3 = new Student();
        student3.setId(3L);
        student3.setName("Joe Johnson");
        student3.setClassId("10A");

        given(studentService.searchStudentsByName("John")).willReturn(Arrays.asList(student1, student3));

        mockMvc.perform(get("/students/search/name?name=John")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Joe Johnson"));
    }
}
