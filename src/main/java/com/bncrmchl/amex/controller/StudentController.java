package com.bncrmchl.amex.controller;

import com.bncrmchl.amex.model.Student;
import com.bncrmchl.amex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/class")
    public ResponseEntity<List<Student>> searchStudentsByClassId(
            @RequestParam(name = "class", required = false) String classId) {
        List<Student> students = studentService.searchStudentsByClassId(classId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Student>> searchStudentsByName(
            @RequestParam(required = false) String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/upsert")
    public ResponseEntity<Student> upsertStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveOrUpdateStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }
}
