package com.bncrmchl.amex.service;

import com.bncrmchl.amex.model.Student;
import com.bncrmchl.amex.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> searchStudentsByClassId(String classId) {
        return studentRepository.findByClassId(classId);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public Student saveOrUpdateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
