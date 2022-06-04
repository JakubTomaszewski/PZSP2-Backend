package com.pzsp2.user.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest =
                new StudentService(studentRepository);
    }


    @Test
    void canGetStudentOptional() {
        //given
        String idNumber = "ADQT#";
        String name = "Jan";
        String surname = "OQHFOQHFQOF";
        //when
        underTest.getStudentOptional(name, surname, idNumber);
        //then
        verify(studentRepository)
                .findByUserFirstNameIgnoreCaseAndUserLastNameIgnoreCaseAndIdNumberIgnoreCase(
                        name, surname, idNumber
                );
    }
}
