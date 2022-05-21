package com.pzsp2.user.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getOrCreateStudent(String name, String surname, String idNumber) {
        Optional<Student> studentOptional =
                studentRepository.findByUserFirstNameIgnoreCaseAndUserLastNameIgnoreCaseAndIdNumberIgnoreCase(
                        name,
                        surname,
                        idNumber);
        if (studentOptional.isEmpty()) {
            Student student = new Student(name, surname, idNumber);
            return studentRepository.save(student);
        } else {
            return studentOptional.get();
        }
    }

    public Optional<Student> getStudentOptional(String name, String surname, String idNumber) {
        return studentRepository.findByUserFirstNameIgnoreCaseAndUserLastNameIgnoreCaseAndIdNumberIgnoreCase(
                name,
                surname,
                idNumber);
    }
}
