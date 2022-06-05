package com.pzsp2.user.teacher;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherResource {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>>getTeachers(){
        return ResponseEntity.ok().body(teacherService.getTeachers());
    }

    @PostMapping("/teacher/save")
    public ResponseEntity<Teacher>saveTeacher(@RequestBody Teacher teacher){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/teacher/save").toUriString());
        return ResponseEntity.created(uri).body(teacherService.saveTeacher(teacher));
    }
}
