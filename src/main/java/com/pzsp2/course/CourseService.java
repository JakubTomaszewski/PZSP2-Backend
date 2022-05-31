package com.pzsp2.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public List<String> getCoursesCodes() {
        List<Course> courses = courseRepository.findAll();
        List<String> codes =
                courses.stream()
                        .map(Course::getCourseCode)
                        .collect(Collectors.toList());
        return codes;
    }
}
