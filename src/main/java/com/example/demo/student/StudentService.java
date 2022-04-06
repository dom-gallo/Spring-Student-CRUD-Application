package com.example.demo.student;

import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmailAddress());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("account already exists for that email");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentDoesExist = studentRepository.existsById(studentId);
        if (!studentDoesExist){
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String emailAddress) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student does not exist for that ID"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (emailAddress != null && emailAddress.length() > 0 && !Objects.equals(student.getEmailAddress(), emailAddress)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(emailAddress);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email already taken");
            }
            student.setEmailAddress(emailAddress);
        }
    }
}
