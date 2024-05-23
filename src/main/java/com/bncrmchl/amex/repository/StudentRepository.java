package com.bncrmchl.amex.repository;

import com.bncrmchl.amex.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContaining(String name);

    List<Student> findByClassId(String classId);

    Optional<Student> findById(Long id);

}

