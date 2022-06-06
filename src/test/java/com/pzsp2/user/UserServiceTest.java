package com.pzsp2.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest =
                new UserService(userRepository);
    }

    @Test
    void canGetAllUsers() {
        //given
        //when
        underTest.getAllUsers();
        //then
        verify(userRepository).findAll();
    }


}
