package com.pzsp2.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course saveCourse(@RequestBody AddCourseRequest request) {
        return courseService.addCourse(request);
    }

}
