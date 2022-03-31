package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("student with this email was registered");
        }
        studentRepository.save(student);
//        repository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new IllegalStateException("student with id " + studentId + " does not exists"));
        if (name != null && name.length() > 0 && !name.equals(student.getName())) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("student with this email was registered");
            }
            student.setEmail(email);
        }
    }
}
