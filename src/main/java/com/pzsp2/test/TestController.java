package com.pzsp2.test;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/tests")
@AllArgsConstructor
public class TestController {
    @Autowired
    final TestService testService;

    @GetMapping(path = "/all")
    public List<Test> displayAllTests() {
        return testService.getAllTests();
    }

    @GetMapping()
    public Test displayTestById(@RequestParam(value = "id") Long id) {
        return testService.getTestById(id);
    }

    @GetMapping(path = "solve")
    public Test displayTestByLink(@RequestParam(value = "link") String link) {
        return testService.getTestByLink(link);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Test saveTest(@RequestBody AddTestRequest request) {
        return testService.addTest(request);
    }
}
