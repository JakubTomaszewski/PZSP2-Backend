package com.pzsp2.course;

import com.pzsp2.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Course addCourse(AddCourseRequest request) {
        //validate course code
        Optional<Course> course = courseRepository.findById(request.getCode());
        if (course.isPresent()) {
            throw new ApiRequestException("Course with that code already exists");
        }
        Course newCourse = new Course();
        newCourse.setCourseCode(request.getCode());
        newCourse.setName(request.getName());

        return courseRepository.save(newCourse);

    }
}
