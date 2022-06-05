package com.pzsp2.user.teacher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class TeacherService implements UserDetailsService{
    private final TeacherRepository teacherRepo;
    private final PasswordEncoder passwordEncoder;

    Teacher saveTeacher(Teacher teacher){
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepo.save(teacher);
    };


    Teacher getTeacher(String login){
        return teacherRepo.findByLogin(login);
    };


    List<Teacher>getTeachers(){
        return teacherRepo.findAll() ;
    };

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Teacher teacher = teacherRepo.findByLogin(login);
        if (teacher == null){
            log.error("Teacher Not Found");
            throw new UsernameNotFoundException("Teacher Not Found in the Database, Please check login");
        }
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(teacher.getAuthority()));

        return new org.springframework.security.core.userdetails.User(
            teacher.getLogin(), teacher.getPassword(), authority);
    }
}
