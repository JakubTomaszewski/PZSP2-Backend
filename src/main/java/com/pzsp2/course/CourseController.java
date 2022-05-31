package com.pzsp2.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(path = "/all")
    public List<String> displayAllCoursesCodes() {
        return courseService.getCoursesCodes();
    }

}
