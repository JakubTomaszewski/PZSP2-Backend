package com.pzsp2.solution;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/solutions")
@AllArgsConstructor
public class SolutionController {
    @Autowired
    final SolutionService solutionService;

    @GetMapping(path = "/test")
    public ResponseEntity<List<StudentTestSolutionPOJO>> displayTestSolutions(@RequestParam(value = "id") Long id) {
        return solutionService.getAllSolutionsByTestId(id);
    }

    //  Remember about duplicate student!!
    @GetMapping
    public ResponseEntity<StudentTestSolutionPOJO> displayTestSolutionsMadeByStudent(
            @RequestBody GetStudentTestSolutionRequest request) {
        return solutionService.getAllTestSolutionsMadeByStudent(request);
    }

    @PostMapping
    public List<Solution> saveSolvedTest(@RequestBody SaveSolutionRequest request) {
        return solutionService.addSolutions(request);
    }
}
